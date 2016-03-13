package de.wenzel.paul.gameframework.util.shaderprograms;

import android.content.Context;
import android.opengl.GLES20;

import de.wenzel.paul.gameframework.util.TextResourceReader;

/**
 * Die abstrakte Klasse {@link ShaderProgram} definiert die grundlegensten Datenfelder und Methoden eines Shaderprogramms.
 * Von dieser Klasse soll jede Klasse erben die ein Shaderprogramm verwaltet.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class ShaderProgram {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	//Context
	private final Context context;

	// Uniform Konstanten
	protected static final String U_MATRIX = "u_Matrix";
	protected static final String U_COLOR = "u_Color";
	protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
	
	// Attribut Konstanten
	protected static final String A_POSITION = "a_Position";    
	protected static final String A_COLOR = "a_Color";    
	protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
	
	// Shaderprogramm
	private final int openglProgramId;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der abstrakten Klasse {@link ShaderProgram}. 
	 * 
	 * @param context welcher Activity der Renderer angehört, um den benötigten Shadercode zu laden
	 * @param vertexShaderResourceId die ID der Datei, welche den Vertexshadercode enthält
	 * @param fragmentShaderResourceId die ID der Datei, welche den Fragmentshadercode enthält
	 */
	protected ShaderProgram(Context context, int vertexShaderResourceId, int fragmentShaderResourceId) {
		// Datenfelder initialisieren
		this.context = context;

		// erzeuge ein Shaderprogramm aus dem gewünschten Vertex- und Fragmentshader
		openglProgramId = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
				TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
	}        
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////

	public int getOpenglProgramId() {
		return openglProgramId;
	}

	public Context getContext() {
		return context;
	}

///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	/**
	 * Die Methode setzt dieses Shaderprogramm als das aktuell zu verwendenen Shaderprogramm.
	 */
	public void useProgram() {
		GLES20.glUseProgram(openglProgramId);
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
