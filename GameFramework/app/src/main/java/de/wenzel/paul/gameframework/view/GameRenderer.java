package de.wenzel.paul.gameframework.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import de.wenzel.paul.gameframework.controller.animation.AnimationInterface;
import de.wenzel.paul.gameframework.model.ViewModelInterface;
import de.wenzel.paul.gameframework.model.dataObjects.ShaderProgramsDataObject;
import de.wenzel.paul.gameframework.model.entities.OpenGlObject;
import de.wenzel.paul.gameframework.util.LoggerConfig;
import de.wenzel.paul.gameframework.util.shaderprograms.ShaderHelper;

/**
 * Die Klasse {@link GameRenderer} ist dafür verantowrtlich die Inhalte auf die GameSurfaceView zu zeichnen.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameRenderer implements Renderer {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/** welcher Activity der Renderer angehört, um Dateien laden zu können */
	private Context context;

	/** beinhaltet eine Ansammlung von allen verfügbaren Shader-Programmen. */
	private ShaderProgramsDataObject shaderProgramsDataObject;

	/** die TransformationMatrix um das Objekt richtig zu Positionieren */
	private float[] projectionAndViewMatrix;

	/** das Model über welches die View alle Objekte hohlen kann die sie zeichnen soll */
	private ViewModelInterface model;
	
	/** das Interface, welches eine Methode enthält, die bei jedem Fram aufgerufen werden soll, um die Objekte zu animieren */
	private AnimationInterface toAnimate;
	
	/** der Zeitpunkt zudem der Letzte Frame gerendert wurde (Zeit in Millisekunden seit Januar 1, 1970 00:00:00.0 UTC) */
	private long lastFrame;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameRenderer}.
	 * 
	 * @param context welcher Activity der Renderer angehört, um Dateien laden zu können
	 * @param model das Model über welches die View alle Objekte hohlen kann die sie zeichnen soll 
	 * @param toAnimate das Interface, welches eine Methode enthält, die bei jedem Fram aufgerufen werden soll, um
	 * 					die Objekte zu animieren
	 */
	public GameRenderer(Context context, ViewModelInterface model, AnimationInterface toAnimate) {
		// Datenfelder initialisieren
		this.model = model;
		this.context = context;

		projectionAndViewMatrix = new float[16];

		this.toAnimate = toAnimate;

		lastFrame = 0;

		shaderProgramsDataObject = null; // kann hier noch nicht initialisiert werden, da dies außerhalb vom OpenGL Thread dann wäre
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////






///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		////////////////////////////////OpenGL Konfigurieren////////////////////////////////

		// die Farbe setzen mit der der Bildschirm immer überzeichnet werden soll
		GLES20.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);

		// PNG-Transperenz erhalten lassen
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);

		////////////////////////////////Shader-Programme laden////////////////////////////////
		shaderProgramsDataObject = new ShaderProgramsDataObject(context);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int screenWidth, int screenHeight) {
		Log.d(getClass().getName(), "width:" + screenWidth);
		Log.d(getClass().getName(), "height:" + screenHeight);

		// Viewport so setzen, dass alles vom Spiel angezeigt wird, und gleichzeitig die Dimensionen von Breite und Höhe beibehalten bleiben.
			// der Viewport gibt an WIE GROSS die Fläche in Pixeln (= Screenkoordinaten) ist, auf welcher das anzuzeigende Bild
			// angezeigt werden soll innerhalb von der SurfaceView
			// außerdem gibt der Viewport an, WO die Fläsche auf der Surface View gezeichnet werden soll
			// (wo die linke untere Ecke des anzuzeigenden Bildes auf der SurfaceView liegt; 0;0 = linke untere Ecke von der SurfaceView)
        float ratioViewPort = model.viewPort().getWidth() / model.viewPort().getHeight();
        float ratioScreen = (float)screenWidth / (float)screenHeight;
        // wenn die Dimensionen vom Screen nicht genauso sind wie die vom Spiel, dann müssen schwarze Balken angezeigt werden
        if (ratioScreen != ratioViewPort) {
            if (ratioScreen < ratioViewPort) {
                int viewportHeight = (int) (screenWidth / ratioViewPort);
                GLES20.glViewport(0, (screenHeight - viewportHeight) /2, screenWidth, viewportHeight);
            } else if (ratioScreen > ratioViewPort) {
                int viewportWidth = (int) (ratioViewPort * screenHeight);
                GLES20.glViewport((screenWidth - viewportWidth) / 2, 0, viewportWidth, screenHeight);
            }
        } else {
            Log.d("View Port", "1");
            // wenn die Dimensionen vom Screen genauso sind wie die vom Spiel, dann müssen keine schwarzen Balken angezeigt werden
            GLES20.glViewport(0, 0, screenWidth, screenHeight);
        }



	    // die orthographische Matrix hohlen um direkt auf den Screen zeichnen zu können
			// gibt die Fläche/Raum in Weltkoordinaten (nicht Screen-Koordinaten) an, welche gezeichnet werden soll (gerendert werden soll)
		float[] projectionMatrix= new float[16];
		Matrix.orthoM(projectionMatrix, 0, model.viewPort().left(), model.viewPort().right(), model.viewPort().bottom(), model.viewPort().top(), 0f, 1f);

		// die Kamera so Positionieren, dass WorldCoordinates = ScreenCoordiantes
			// Richtet die kamera aus, um die Perspektive aus welche der zu zeichnende Raum (definiert durch die Projectionmatrix) betrachtet werden soll, um ein 2D Bild zu erhalten
			// Frag: wird kamera in World koordinates ausgerichtet?
		float[] viewMatrix = new float[16];
		Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // die Projektions- und Viewtransformation berrechnen mit welcher jeder Pukt multipliziert werden muss
		Matrix.multiplyMM(projectionAndViewMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// alle Objekte animieren
		long renderDuration = System.currentTimeMillis() - lastFrame;
		toAnimate.animate(renderDuration);
		lastFrame += renderDuration;

		if (LoggerConfig.VERBOSE) Log.d(GameRenderer.class.getClass().getName(), "renderDuration: " + renderDuration);

		// jeden Pixel mit der zuvor in onSurfaceCreated() festgelegten Farbe überzeichnen
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

		////////////Zeichnen////////////
		for (OpenGlObject entity : model.entitiesToDraw()) {
			entity.draw(projectionAndViewMatrix, shaderProgramsDataObject);
		}

		// prüfen ob OpenGL Errors aufgekommen sind
		if (LoggerConfig.VERBOSE) ShaderHelper.checkOpenglError(GameRenderer.class.getClass().getName());
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode wird von der GameActivity aufgerufen, wenn die Activity fortgesetzt wird.
	 */
	public void onResume() {
		// lastFrame setzen
		lastFrame = System.currentTimeMillis();
    }
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	

}
