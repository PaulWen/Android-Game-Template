package de.wenzel.paul.gameframework.model.entities.skills;


/**
 * Die Klasse {@link DestroyableSkill} fasst alle nötigen Informationen zusammen, die ein zerstörbares Objekt 
 * speichern muss. Außerdem stellt es die nötigen Methoden zur Manipulation dieser Werte zur Verfügung.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class DestroyableSkill {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** die maximale Anzahl an Leben die das Objekt jemals hatte */
	private int maxLives;
	
	/** die Anzahl an Leben die das Objekt noch hat (0 = zerstört) */
	private int lives;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link DestroyableSkill}.
	 * 
	 * @param lives die Anzahl an Leben die das Objekt noch hat (0 = zerstört)
	 */
	public DestroyableSkill(int lives) {
		// Datenfelder initialisieren
		maxLives = lives;
		this.lives = lives;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die maximale Anzahl an Leben aus. 
	 * 
	 * @return die maximale Anzahl an Leben
	 */
	public int getMaxLives() {
		return maxLives;
	}
	
	/**
	 * Die Methode gibt die Anzahl an Leben die das Objekt noch hat (0 = zerstört) aus.
	 * 
	 * @return die Anzahl an Leben die das Objekt noch hat (0 = zerstört)
	 * 		   falls NULL, dann besitzt das Objekt diese Fähigkeit nicht!
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Die Methode zält das Leben runter.
	 * 
	 * @param destructivePower um wie viele Lebenspunkte das Leben runtergezählt werden soll
	 */
	public void countdownLives(int destructivePower) {
		lives -= destructivePower;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
