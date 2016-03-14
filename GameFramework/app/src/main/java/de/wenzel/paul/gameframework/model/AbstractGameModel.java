package de.wenzel.paul.gameframework.model;

import de.wenzel.paul.gameframework.Config;
import de.wenzel.paul.gameframework.util.Rectangle;

/**
 * Die Klasse {@link AbstractGameModel} ist das Model vom Spiel. Es speichert und verwaltet alle Objekte auf dem Spielfeld.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class AbstractGameModel implements ViewModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/**
	 * dient dazu zu definieren, welcher Bereich aus der Welt dme Spieler gezeigt werden soll.
	 * (Der Bereich wird in Weltkoordinaten angegeben)
	 */
	private Rectangle viewPort;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link AbstractGameModel}.
	 */
	public AbstractGameModel() {
		// Datenfelder initialisieren
		viewPort = new Rectangle(0, 0, Config.viewPortWidthInWorldcoordinates, Config.viewPortHeightInWorldcoordinates);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	public Rectangle viewPort() {
		return viewPort;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


/////////////////////////////////////////////Abstrakte Methoden//////////////////////////////////////////////


///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
}
