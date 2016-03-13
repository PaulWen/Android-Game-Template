package de.wenzel.paul.gameframework.model.entities;

import android.opengl.GLES20;

import de.wenzel.paul.gameframework.model.dataObjects.ShaderProgramsDataObject;

/**
 * Die abstrakte Klasse {@link ColoredOpenGlObject} enthält alle Informationen, um das Objekt mit einer Texture in OpenGL zu zeichnen.
 * Falls die Entities weitere Fähigkeiten haben möchten (z.B. zerstörbar, beweglich...) so müssen die die Passenden
 * SkillInterfaces implementieren und werden dadurch gezwungen die SkilObjekte zu haben.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ColoredOpenGlObject extends OpenGlObject {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /** Die Farbe in welchem das Objekt gezeichnet werden soll. (generieren über Color.argb()) */
    private int color;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////

	/**
	 * Der Konstruktor der abstrakten Klasse {@link ColoredOpenGlObject}.
	 * HINWEIS: Nur die Paramter angeben die zu den gewünschten Fähigkeit gehören! Die anderen auf NULL setzen!
	 *
	 * @param x x-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y y-Koordinate der unteren-linken-Ecke des Objektes
	 * @param width Breite des Objekts
	 * @param height Höhe des Objekts
     * @param color  Die Farbe in welchem das Objekt gezeichnet werden soll. (generieren über Color.argb())
	 */
	public ColoredOpenGlObject(float x, float y, float width, float height, int color) {
		super(x, y, width, height);

		// Datenfelder initialisieren
        this.color = color;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	public void draw(float[] projectionAndViewMatrix, ShaderProgramsDataObject shaderProgramsDataObject) {
        //das richtige Shader-Programm aktivieren
        shaderProgramsDataObject.getColorShaderProgram().useProgram();

        // die Uniforms setzen
        shaderProgramsDataObject.getColorShaderProgram().setUniforms(projectionAndViewMatrix, color);

        // der Vertex-Variable des VertexShaders die Daten geben
        GLES20.glVertexAttribPointer(shaderProgramsDataObject.getColorShaderProgram().getPositionAttributeLocation(), OpenGlObject.POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData()); // den FloatBuffer mit den Vertex-Daten der Vertex-Variable des VertexShaders zuweisen

        // die Verknüpften Daten freigeben
        GLES20.glEnableVertexAttribArray(shaderProgramsDataObject.getColorShaderProgram().getPositionAttributeLocation());

        // die ersten 6 Punkte als zwei Dreicke zeichnen
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        // die Verknüpften Daten wieder lösen
        GLES20.glDisableVertexAttribArray(shaderProgramsDataObject.getColorShaderProgram().getPositionAttributeLocation());
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
