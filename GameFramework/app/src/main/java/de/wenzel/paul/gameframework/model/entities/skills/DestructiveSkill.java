package de.wenzel.paul.gameframework.model.entities.skills;


/**
 * Die Klasse {@link DestructiveSkill} fasst alle nötigen Informationen zusammen, die ein zerstörerisches Objekt 
 * speichern muss. Außerdem stellt es die nötigen Methoden zur Manipulation dieser Werte zur Verfügung.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class DestructiveSkill {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** die Anzahl an Leben die das Objekt abzieht bei jedem anderen Objekt das es berührt */
	private int destructivePower;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link DestructiveSkill}.
	 * 
	 * @param destructivePower die Anzahl an Leben die das Objekt abzieht bei jedem anderen Objekt das es berührt
	 */
	public DestructiveSkill(int destructivePower) {
		// Datenfelder initialisieren
		this.destructivePower = destructivePower;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die Anzahl an Leben die das Objekt abzieht aus.
	 * 
	 * @return die Anzahl an Leben die das Objekt abzieht (power > 0)
	 */
	public int getDestructivePower() {
		return destructivePower;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
