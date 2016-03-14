package examplegame;

import android.graphics.Color;

import java.util.ArrayList;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.model.AbstractGameModel;
import de.wenzel.paul.gameframework.model.entities.ColoredOpenGlObject;
import de.wenzel.paul.gameframework.model.entities.OpenGlObject;
import de.wenzel.paul.gameframework.model.entities.TexturedOpenGlObject;

/**
 * Die Klasse {@link GameModel} ist das Model vom Spiel. Es speichert und verwaltet alle Objekte auf dem Spielfeld.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameModel extends AbstractGameModel {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameModel}. 
	 */
	public GameModel() {
		// Datenfelder initialisieren
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public ArrayList<OpenGlObject> entitiesToDraw() {
		ArrayList<OpenGlObject> entitiesToDraw = new ArrayList<OpenGlObject>();

		entitiesToDraw.add(new TexturedOpenGlObject( 0, 0, 16000f, 9000f, new int[]{R.drawable.background}));
		entitiesToDraw.add(new ColoredOpenGlObject(100, 100, 5000, 3000, Color.rgb(250, 40, 5)));

		return entitiesToDraw;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
}
