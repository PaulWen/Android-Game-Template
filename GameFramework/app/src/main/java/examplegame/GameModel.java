package examplegame;

import android.graphics.Color;

import java.util.ArrayList;

import de.wenzel.paul.gameframework.Config;
import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.model.ViewModelInterface;
import de.wenzel.paul.gameframework.model.entities.ColoredOpenGlObject;
import de.wenzel.paul.gameframework.model.entities.OpenGlObject;
import de.wenzel.paul.gameframework.model.entities.TexturedOpenGlObject;
import de.wenzel.paul.gameframework.util.Rectangle;

/**
 * Die Klasse {@link GameModel} ist das Model vom Spiel. Es speichert und verwaltet alle Objekte auf dem Spielfeld.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameModel implements ViewModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/**
	 * dient dazu zu definieren, welcher Bereich aus der Welt dme Spieler gezeigt werden soll.
	 * (Der Bereich wird in Weltkoordinaten angegeben)
	 */
	private Rectangle viewPort;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameModel}. 
	 */
	public GameModel() {
		// Datenfelder initialisieren
		viewPort = new Rectangle(0, 0, Config.viewPortWidthInWorldcoordinates, Config.viewPortHeightInWorldcoordinates);
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

	public Rectangle viewPort() {
		return viewPort;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
}
