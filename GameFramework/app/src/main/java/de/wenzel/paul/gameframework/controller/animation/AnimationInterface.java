package de.wenzel.paul.gameframework.controller.animation;


/**
 * Das Interface {@link AnimationInterface} definiert alle Methoden, die eine Animtionsklasse implementieren muss.
 * Somit soll jede Animationsklasse dieses Interface implementieren!
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface AnimationInterface {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode startet die Animation.
	 * 
	 * @param loopDuration wie lange das letzte Update her ist (in Millisekunden)
	 */
	public void animate(long loopDuration);
	
}
