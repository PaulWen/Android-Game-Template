package de.wenzel.paul.gameframework.model.entities;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import de.wenzel.paul.gameframework.model.dataObjects.ShaderProgramsDataObject;
import de.wenzel.paul.gameframework.util.TextureHelper;

/**
 * Die abstrakte Klasse {@link TexturedOpenGlObject} enthält alle Informationen, um das Objekt mit einer Texture in OpenGL zu zeichnen.
 * Falls die Entities weitere Fähigkeiten haben möchten (z.B. zerstörbar, beweglich...) so müssen die die Passenden
 * SkillInterfaces implementieren und werden dadurch gezwungen die SkilObjekte zu haben.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class TexturedOpenGlObject extends OpenGlObject {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/** Buffer mit den Texturkoordinaten von diesem Objekt */
	private FloatBuffer textureVertexData;

	/** eine Liste mit allen spriteResourceIDs der gewünschten Texturen */
	private int[] spriteResourceIDList;
	/** der Index der gewünschten Textur aus der spriteResourceIDList */
	private int currentSpriteIndexNumber;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der abstrakten Klasse {@link TexturedOpenGlObject}. 
	 * HINWEIS: Nur die Paramter angeben die zu den gewünschten Fähigkeit gehören! Die anderen auf NULL setzen!
	 * 
	 * @param x x-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y y-Koordinate der unteren-linken-Ecke des Objektes
	 * @param width Breite des Objekts
	 * @param height Höhe des Objekts
	 * @param spriteResourceIDList die ResourceID, welche der gewüschten Bilddatei zugeordnet ist
	 */
	public TexturedOpenGlObject(float x, float y, float width, float height, int[] spriteResourceIDList) {
		super(x, y, width, height);

		// Datenfelder initialisieren
		float[] textureVertices = new float[] {
				// Ordnung der koordinaten: S, T
				0, 0,
				0, -1,
				1, -1,

				1, -1,
				1, 0,
				0, 0
		};

		// The vertex buffer.
		textureVertexData = ByteBuffer
				.allocateDirect(textureVertices.length * BYTES_PER_FLOAT) // den benötigten Speicherplatz resavieren
				.order(ByteOrder.nativeOrder()) // auslesen wie herum das verwendete System Bytes liest (Endianness - Byte-Reihenfolge) und diese Leserichtung für den ByteBuffer ebenfalls einstellen
				.asFloatBuffer(); // aus dem ByteBuffer ein FloatBuffer machen
		textureVertexData.put(textureVertices); // den erstellten FloatBuffer mit den Koordinaten füllen
		textureVertexData.position(0); // den Zeiger des FloatBuffers auf das erste Elemnet setzen

		this.spriteResourceIDList = spriteResourceIDList;
		currentSpriteIndexNumber = 0;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt eine Liste mit allen ResourceIDs der gewünschten Texturen aus.
	 * 
	 * @return
	 */
	public int[] getSpriteResourceIDList() {
		return spriteResourceIDList;
	}
	/**
	 * Die Methode gibt die ResourceID der aktuell gewünschten Texture aus.
	 * 
	 * @return die ResourceID der gewünschten Texture
	 */
	public int getCurrentSpriteResourceID() {
		return spriteResourceIDList[currentSpriteIndexNumber];
	}
	/**
	 * Die Methode sorg dafür, dass die nächste vorhhandene Textur als die anzuzeigende gesetzt wird.
	 * Falls breits die letzte vorhandene Textur ausgeählt ist wird weiterhin diese angezeigt.
	 */
	public void nextSprite() {
		if (currentSpriteIndexNumber + 1 != spriteResourceIDList.length) {
			currentSpriteIndexNumber++;
		}
	}
	/**
	 * Die Methode sorg dafür, dass die erste Textur als die anzuzeigende gesetzt wird.
	 */
	public void firstSprite() {
		currentSpriteIndexNumber = 0;
	}
	/**
	 * Die Methode gibt aus der wie vielte Texture gerade angezeigt wird.
	 * 
	 * @return die wie vielte Texture gerade angezeigt wird (Bsp.: 0 = 1. Texture)
	 */
	public int getCurrentSpriteIndexNumber() {
		return currentSpriteIndexNumber;
	}
	
	/**
	 * Die Methode gibt die Anzahl an Texturen die es für das Objekt gibt aus.
	 * 
	 * @return Anzahl an Texturen für das Objekt
	 */
	public int getNumberOfSprites() {
		return spriteResourceIDList.length;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	public void draw(float[] projectionAndViewMatrix, ShaderProgramsDataObject shaderProgramsDataObject) {
		//das richtige Shader-Programm aktivieren
		shaderProgramsDataObject.getTextureShaderProgram().useProgram();

		// die Uniforms setzen
		shaderProgramsDataObject.getTextureShaderProgram().setUniforms(projectionAndViewMatrix, TextureHelper.loadTexture(shaderProgramsDataObject.getTextureShaderProgram().getContext(), getCurrentSpriteResourceID()));

		// der Vertex-Variable des VertexShaders die Daten geben
		GLES20.glVertexAttribPointer(shaderProgramsDataObject.getTextureShaderProgram().getPositionAttributeLocation(), OpenGlObject.POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData()); // den FloatBuffer mit den Vertex-Daten der Vertex-Variable des VertexShaders zuweisen
		GLES20.glVertexAttribPointer(shaderProgramsDataObject.getTextureShaderProgram().getTextureCoordinatesAttributeLocation(), OpenGlObject.TEXTURE_COORDINATES_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, textureVertexData); // den FloatBuffer mit den Vertex-Daten der Vertex-Variable des VertexShaders zuweisen

		// die Verknüpften Daten freigeben
		GLES20.glEnableVertexAttribArray(shaderProgramsDataObject.getTextureShaderProgram().getPositionAttributeLocation());
		GLES20.glEnableVertexAttribArray(shaderProgramsDataObject.getTextureShaderProgram().getTextureCoordinatesAttributeLocation());

		// die ersten 6 Punkte als zwei Dreicke zeichnen
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

		// die Verknüpften Daten wieder lösen
		GLES20.glDisableVertexAttribArray(shaderProgramsDataObject.getTextureShaderProgram().getPositionAttributeLocation());
		GLES20.glDisableVertexAttribArray(shaderProgramsDataObject.getTextureShaderProgram().getTextureCoordinatesAttributeLocation());
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
