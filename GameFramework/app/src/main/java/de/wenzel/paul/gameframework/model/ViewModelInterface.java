package de.wenzel.paul.gameframework.model;


import java.util.ArrayList;

import de.wenzel.paul.gameframework.model.entities.OpenGlObject;
import de.wenzel.paul.gameframework.util.Rectangle;


/**
 * Das Interface {@link ViewModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface ViewModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt eine Liste mit allen Objekten aus, welche gezeichnet werden sollen.
	 * Die Reihenfolge in welcher die Objekte in der Liste stehen ist auch die Reihenfolge,
	 * in welcher sie gezeichnet werden. (ist wird mit dem Element mit dem Index 0 angefangen zu zeichnen!)
	 * 
	 * @return Liste mit allen Objekten, die gezeichnet werden sollen
	 */
	public ArrayList<OpenGlObject> entitiesToDraw();

	/**
	 * Die Methode, gibt den ViewPort aus, wlecher definiert, welcher ausschnitt aus dem Spiel angezeigt werden soll
	 *
	 * @return der ViewPort dargestellt als ein {@link Rectangle}
	 * 				  (	-die x-Koordinate (Weltkoordinate) der untersten Linken Ecke der Welt, welche angezeigt werden soll
	 * 					-die y-Koordinate (Weltkoordinate) der untersten Linken Ecke der Welt, welche angezeigt werden soll
	 * 					-wie viel von der Welt in der Breite angezeigt werden soll
	 * 					-wie viel von der Welt in der Höhe angezeigt werden soll)
	 */
	public Rectangle viewPort();

}
