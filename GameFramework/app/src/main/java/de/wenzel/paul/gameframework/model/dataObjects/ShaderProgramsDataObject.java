package de.wenzel.paul.gameframework.model.dataObjects;

import android.content.Context;

import de.wenzel.paul.gameframework.util.shaderprograms.ColorShaderProgram;
import de.wenzel.paul.gameframework.util.shaderprograms.TextureShaderProgram;

/**
 * Die Klasse {@link ShaderProgramsDataObject} beinhaltet eine Ansammlung von allen verfügbaren
 * Shader-Programmen.
 *
 * @author Paul Wenzel
 */
public class ShaderProgramsDataObject {

////////////////////////////////////////////Datenfelder////////////////////////////////////////////

    /** das TextureShaderProgram welches zum zeichnen von Objekten mit Texturen verwendet werden soll */
    private TextureShaderProgram textureShaderProgram;
    /** das ColorShaderProgram welches zum zeichnen von Objekten mit EINER Farbe verwendet werden soll */
    private ColorShaderProgram colorShaderProgram;

////////////////////////////////////////////Konstruktor////////////////////////////////////////////

    /**
     * Der Konstruktor der Klasse {@link ShaderProgramsDataObject}.
     *
     * @param context welcher Activity der Renderer angehört, um auf die gewünschte Bilddatei zugreifen zu können
     */
    public ShaderProgramsDataObject(Context context) {
        // Datenfelder initialisieren
            //TextureShaderProgramm laden
        textureShaderProgram = new TextureShaderProgram(context);
            // ColorShaderProgram laden
        colorShaderProgram = new ColorShaderProgram(context);
    }

/////////////////////////////////////////Getter und Setter/////////////////////////////////////////

    public ColorShaderProgram getColorShaderProgram() {
        return colorShaderProgram;
    }

    public TextureShaderProgram getTextureShaderProgram() {
        return textureShaderProgram;
    }

//////////////////////////////////////////geerbte Methoden/////////////////////////////////////////


/////////////////////////////////////////////Methoden//////////////////////////////////////////////


//////////////////////////////////////////Innere Klassen///////////////////////////////////////////


}