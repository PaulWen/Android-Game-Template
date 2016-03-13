package de.wenzel.paul.gameframework.util.shaderprograms;

import android.opengl.GLES20;
import android.util.Log;

import de.wenzel.paul.gameframework.util.LoggerConfig;


/**
 * Die Klasse {@link ShaderHelper} hilft beim ertellen von Shader-Programme.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ShaderHelper {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	

	
	 
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ShaderHelper}. 
	 */
	public ShaderHelper() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode lädt und kompeliert einen VertexShader. Anschließend gibt sie dessen ID aus.
	 * 
	 * @param vertexShaderCode der Quellcode des VertexShaders
	 * @return die ID des VertexShaders
	 */
    private static int compileVertexShader(String vertexShaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
    }
    
    /**
	 * Die Methode lädt und kompeliert einen FragmentShader. Anschließend gibt sie dessen ID aus.
	 * 
	 * @param fragmentShaderCode der Quellcode des FragmentShaders
	 * @return die ID des FragmentShaders
	 */
    private static int compileFragmentShader(String fragmentShaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
    }
    
    /**
     * Die Methode dient dazu einen Shader zu kompelieren.
     * Anschließend gibt sie dessen ID aus, oder 0, wenn ein Fehler aufgetreten ist.
     * 
     * @param type der Typ des Shaders (VertexShader --> GLES20.GL_VERTEX_SHADER;
     * 									FragmentShader --> GLES20.GL_FRAGMENT_SHADER)
     * @param shaderCode der Quellcode des Shaders
     * @return die ID des Shaders, oder 0, wenn ein Fehler aufgetreten ist
     */
    private static int compileShader(int type, String shaderCode) {
        // ein neues Shader-Objekt erstellen und dessen ID abspeichern
        final int shaderObjectId = GLES20.glCreateShader(type);

        // prüfen ob es einen Fehler gab
        if (shaderObjectId == 0) {
        	if (LoggerConfig.ERROR) Log.e(ShaderHelper.class.getClass().getName(), "Es konnte kein Shader erzeugt werden.");
            return 0;
        }
       
        // den Shader mit dem Quellcode füllen
        GLES20.glShaderSource(shaderObjectId, shaderCode);

        // den Shader kompelieren
        GLES20.glCompileShader(shaderObjectId);

        // den Kompieler-Status auslesen und ausgeben
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        if (LoggerConfig.VERBOSE) Log.v(ShaderHelper.class.getClass().getName(), "Ergebnis des kompelierens vom Shader:" + compileStatus[0] +
        																	     "  Log:" + GLES20.glGetShaderInfoLog(shaderObjectId));

        // den Kompieler-Status überprüfen
        if (compileStatus[0] == 0) {
            // wenn das Kompelieren des Shaders fehlgeschlagen ist das Shader-Objekt löschen
        	GLES20.glDeleteShader(shaderObjectId);

        	if (LoggerConfig.ERROR) Log.e(ShaderHelper.class.getClass().getName(), "Der Shader konnte nicht kompeliert werden.");
            return 0;
        }

        // die ID des fertigen Shader-Objekt zurückgeben
        return shaderObjectId;
    }
    
    /**
     * Die Methode verknüpft einen VertexShader und einen FragmentShader zusammen in ein OpenGL-Programm.
     * Anschließend gibt die ID von diesem Programm aus, oder 0, wenn ein Fehler aufgetreten ist.
     * 
     * @param vertexShaderId die ID des VertexShaders
     * @param fragmentShaderId die ID des FragmentShaders
     * @return die ID des OpenGL-Programm, oder 0, wenn ein Fehler aufgetreten ist
     */
    private static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        // erstelle ein neues OpenGL-Programm-Objekt und speichere dessen ID ab
        final int programObjectId = GLES20.glCreateProgram();
		
        // prüfen ob es einen Fehler gab
        if (programObjectId == 0) {
        	if (LoggerConfig.ERROR) Log.e(ShaderHelper.class.getClass().getName(), "Es konnte kein OpenGL-Programm erzeugt werden.");
            return 0;
        }

        // füge den VertaxShader zum OpenGL-Programm hinzu
        GLES20.glAttachShader(programObjectId, vertexShaderId);
        // füge den FragmentShader zum OpenGL-Programm hinzu
        GLES20.glAttachShader(programObjectId, fragmentShaderId);

        // verknüpfe die beiden Shaders in einem OpenGL-Programm
        GLES20.glLinkProgram(programObjectId);

        // den Status des Verknüpfen auslesen und ausgeben
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);

        if (LoggerConfig.VERBOSE) Log.v(ShaderHelper.class.getClass().getName(), "Ergebnis des Verknüpfen der Shaders in einem OpenGL-Programm:" + linkStatus[0] +
        																		  " Log:" + GLES20.glGetProgramInfoLog(programObjectId));

		// den Status des Verknüpfen überprüfen
		if (linkStatus[0] == 0) {
			// wenn das Verknüpfen der Shaders in ein OpenGL-Programm fehlgeschlagen ist das OpenGL-Programm-Objekt löschen
			GLES20.glDeleteProgram(programObjectId);
			
			if (LoggerConfig.ERROR) Log.e(ShaderHelper.class.getClass().getName(), "Die Shaders konnten nicht zu einem OpenGL-Programm verbunden werden.");
			return 0;
		}

		// wenn sich die App noch in der Entwicklung befindet das fertige OpenGL-Programm überprüfen
		if (LoggerConfig.VERBOSE) {
			if (!validateProgram(programObjectId)) {
			    Log.e(ShaderHelper.class.getClass().getName(), "Die Shaders konnten nichterfolgreich validiert werden.");
				return 0;
			}
		}
		
        // die ID des OpenGL-Programm-Objekt zurückgeben
        return programObjectId;
    }
    
    /**
     * Die Methode überprüft, ob ein OpenGL-Programm richtig und funktionstüchtig ist.
     * HINWEIS: Diese Methode sollte nur während der Entwicklung aufgerufen werden!
     * 
     * @param programObjectId die ID des OpenGL-Programms
     * @return true: das OpenGL-Programm ist funktionstüchtig;
     * 		   false: das OpenGL-Programm ist nicht funktionstüchtig
     */
    private static boolean validateProgram(int programObjectId) {
    	// das OpenGL-Programm überprüfen
    	GLES20.glValidateProgram(programObjectId);
		
    	// das Ergebnis der Überprüfung auslesen und ausgeben
        final int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);

        if (LoggerConfig.VERBOSE) Log.v(ShaderHelper.class.getClass().getName(), "Ergebnis der Überprüfung des OpenGL-Programms:" + validateStatus[0] +
        																		 " Log:" + GLES20.glGetProgramInfoLog(programObjectId));

        // das Ergebnis der Überprüfung zurückgeben
        return validateStatus[0] != 0;
    }
    
    /**
     * Die Methode kompeliert einen Vertex- und Fragmentshader, verlinkt sie in einem Shaderprogram zusammen,
     * validiret das Programm und gibt die ID des fertigen Shaderprogramms zurück.
     * 
     * @param vertexShaderCode der Quellcode des Vertexshaders
     * @param fragmentShaderCode der Quellcode des Fragmentshaders
     * 
     * @return die ID des OpenGL-Programms, welches aus den beiden gewünschten Shaders besteht
     */
    public static int buildProgram(String vertexShaderCode, String fragmentShaderCode) {
        int programObjectId;

        // Shaders kompilieren
        int vertexShader = compileVertexShader(vertexShaderCode);
        int fragmentShader = compileFragmentShader(fragmentShaderCode);

        // Shaders in einem Shaderprogram verlinken
        programObjectId = linkProgram(vertexShader, fragmentShader);

        // wenn geünscht das Shaderprogramm validieren
        if (LoggerConfig.VERBOSE) validateProgram(programObjectId);

        // die ID des fertigen Shaderprograms zurück geben
        return programObjectId;
    }

    /**
     * Die Methode guckt, ob in OpenGL Fehler aufgetreten sind und gibt falls ja die Fehler Codes aus.
     *
     * @param positionInCode ein String welcher beschreibt, wo im Code diese Funktion aufgerufen wurde.
     */
    public static void checkOpenglError(String positionInCode) {
        if (LoggerConfig.VERBOSE) {
            int errorCode;
            while ((errorCode = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
                Log.e(positionInCode, "glError " + errorCode);
            }
        }
    }
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	

}
