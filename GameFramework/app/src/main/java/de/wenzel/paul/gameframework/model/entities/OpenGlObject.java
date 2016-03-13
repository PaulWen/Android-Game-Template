package de.wenzel.paul.gameframework.model.entities;

import java.io.Serializable;
import java.nio.FloatBuffer;

import de.wenzel.paul.gameframework.model.dataObjects.ShaderProgramsDataObject;
import de.wenzel.paul.gameframework.util.Rectangle;

/**
 * Die Klasse {@link OpenGlObject} [...]
 *
 * @author Paul Wenzel
 */
public abstract class OpenGlObject implements Serializable {

/////////////////////////////////////////////////Konstanten/////////////////////////////////////////////////

/** Anzahl der Bytes(8-bit) pro Float(32-bit) */
public static final int BYTES_PER_FLOAT = 4;
/** Anzahl der Koordinaten, die einen Koordinatenpunkt des Objektes repräsentieren */
public static final int POSITION_COMPONENT_COUNT = 2;
/** Anzahl der Koordinaten, die einen Texturpunkt repräsentieren */
public static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;

////////////////////////////////////////////Datenfelder////////////////////////////////////////////

    /** die Größe und Position vom Objekt */
    private Rectangle entityDimensions;

////////////////////////////////////////////Konstruktor////////////////////////////////////////////

    /**
     * Der Konstruktor der Klasse {@link OpenGlObject}.
     */
    public OpenGlObject(float x, float y, float width, float height) {
        // Datenfelder initialisieren
        entityDimensions = new Rectangle(x, y, width, height);
    }

/////////////////////////////////////////Getter und Setter/////////////////////////////////////////

    public Rectangle getEntityDimensions() {
        return entityDimensions;
    }

//////////////////////////////////////////geerbte Methoden/////////////////////////////////////////


/////////////////////////////////////////////Methoden//////////////////////////////////////////////

    /**
     * Die Methode generiert aus den Dimensionen die Vertex-Daten, welche OpenGL brauch, um das Object als
     * Rechteck in den richtigen Dimensionen an die richtige Stelle zu zeichnen.
     *
     * @return Buffer mit den Weltkoordinaten von diesem Objekt
     */
    protected FloatBuffer vertexData() {
        return entityDimensions.generateVertexData();
    }

    /**
     * Die Methode zeichnet das Objekt auf den Bildschirm.
     *
     * @param projectionAndViewMatrix die TransformationMatrix um das Objekt richtig zu Positionieren
     * @param shaderProgramsDataObject beinhaltet eine Ansammlung von allen verfügbaren Shader-Programmen.
     */
    public abstract void draw(float[] projectionAndViewMatrix, ShaderProgramsDataObject shaderProgramsDataObject);

//////////////////////////////////////////Innere Klassen///////////////////////////////////////////


}