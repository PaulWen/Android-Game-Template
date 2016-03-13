package de.wenzel.paul.gameframework.util.shaderprograms;

import android.content.Context;
import android.opengl.GLES20;

import de.wenzel.paul.gameframework.R;

/**
 * Die Klasse {@link TextureShaderProgram} ist ein Shaderprogramm, welches Objekte mit Texturen zeichnen kann.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class TextureShaderProgram extends ShaderProgram {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	// die IDs der Uniforms des Shaderprogramms
	private final int uMatrixLocation;
	private final int uTextureUnitLocation;
	
	// die IDs der Attribute des Shaderprogramms
	private final int aPositionLocation;
	private final int aTextureCoordinatesLocation;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link TextureShaderProgram}. 
	 * 
	 * @param context welcher Activity der Renderer angehört, um den benötigten Shadercode zu laden
	 */
	public TextureShaderProgram(Context context) {
		super(context, R.raw.texture_vertex_shader, R.raw.texture_fragment_shader);
		
		// Datenfelder initialisieren
		uMatrixLocation = GLES20.glGetUniformLocation(getOpenglProgramId(), U_MATRIX);
		uTextureUnitLocation = GLES20.glGetUniformLocation(getOpenglProgramId(), U_TEXTURE_UNIT);
		
		aPositionLocation = GLES20.glGetAttribLocation(getOpenglProgramId(), A_POSITION);
		aTextureCoordinatesLocation = GLES20.glGetAttribLocation(getOpenglProgramId(), A_TEXTURE_COORDINATES);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode ermöglicht es die Uniforms des Shaderprogramms zu setzen.
	 * 
	 * @param matrix die Projektions-View-transformation-Matrix 
	 * @param textureId die ID der gewünschten Textur
	 */
	public void setUniforms(float[] matrix, int textureId) {
		GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

		// setze "GL_TEXTURE0" als aktive Textur
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		// binde die gewünschte Textur zur aktiven Textur
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
		// dem "texture uniform sampler" sagen, dass er die Textur "GLES20.GL_TEXTURE0" (was gleich is 0) verwenden soll
		GLES20.glUniform1i(uTextureUnitLocation, 0);
	}
	
	/**
	 * Die Methode gibt die ID des Atrributs aus, über welches der Vertexshader die Vertexdaten entgegennimmt.
	 * 
	 * @return die ID des Atrributs, über welches der Vertexshader die Vertexdaten entgegennimmt
	 */
	public int getPositionAttributeLocation() {
		return aPositionLocation;
	}
	
	/**
	 * Die Methode gibt die ID des Atrributs aus, über welches der Vertexshader die Vertexdaten
	 * für die Textur entgegennimmt.
	 * 
	 * @return die ID des Atrributs, über welches der Vertexshader die Vertexdaten für die Textur entgegennimmt
	 */
	public int getTextureCoordinatesAttributeLocation() {
		return aTextureCoordinatesLocation;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
