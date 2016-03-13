package de.wenzel.paul.gameframework.model.entities.skills;


/**
 * Die Klasse {@link MoveableSkill} fasst alle nötigen Informationen zusammen, die ein bewegliches Objekt 
 * speichern muss. Außerdem stellt es die nötigen Methoden zur Manipulation dieser Werte zur Verfügung.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class MoveableSkill {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** wie schnell sich das Objekt HÖCHSTENS auf der x-Achse bewegen kann (Pixel pro Sekunde) */
	private float maxVelocityX;
	/** wie schnell sich das Objekt HÖCHSTENS auf der y-Achse bewegen kann (Pixel pro Sekunde) */
	private float maxVelocityY;
	
	/** wie schnell sich das Objekt auf der x-Achse bewegen kann (Pixel pro Sekunde) */
	private float velocityX;
	/** wie schnell sich das Objekt auf der y-Achse bewegen kann (Pixel pro Sekunde) */
	private float velocityY;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link MoveableSkill}.
	 * 
	 * @param maxVelocityX wie schnell sich das Objekt HÖCHSTENS auf der x-Achse bewegen kann (Pixel pro Sekunde)
	 * @param maxVelocityX wie schnell sich das Objekt HÖCHSTENS auf der y-Achse bewegen kann (Pixel pro Sekunde)
	 */
	public MoveableSkill(float maxVelocityX, float maxVelocityY) {
		// Datenfelder initialisieren
		this.maxVelocityX = maxVelocityX;
		this.maxVelocityY = maxVelocityY;
		
		velocityX = 0;
		velocityY = 0;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt aus, wie schnell sich das Objekt HÖCHSTENS auf der x-Achse bewegen kann (Pixel pro Sekunde).
	 * 
	 * @return wie schnell sich das Objekt HÖCHSTENS auf der x-Achse bewegen kann (Pixel pro Sekunde)
	 */
	public float getMaxVelocityX() {
		return maxVelocityX;
	}
	/**
	 * Die Methode gibt aus, wie schnell sich das Objekt HÖCHSTENS auf der y-Achse bewegen kann (Pixel pro Sekunde).
	 * 
	 * @return wie schnell sich das Objekt HÖCHSTENS auf der y-Achse bewegen kann (Pixel pro Sekunde)
	 */
	public float getMaxVelocityY() {
		return maxVelocityY;
	}

	
	/**
	 * Die Methode gibt aus, wie schnell sich das Objekt auf der x-Achse bewegen kann (Pixel pro Sekunde).
	 * 
	 * @return wie schnell sich das Objekt auf der x-Achse bewegen kann (Pixel pro Sekunde)
	 */
	public float getVelocityX() {
		return velocityX;
	}
	/**
	 * Die Methode ermöglicht es die aktuelle Geschwindigkeit auf der x-Achse des Objektes zu setzen.
	 * Maximal kann der Wert dabei +/- maxVelocityX annehmen!
	 * 
	 * @param velocityX die gewünschte aktuelle Geschwindigkeit des Objektes
	 */
	public void setVelocityX(float velocityX) {
		if (velocityX <= maxVelocityX && velocityX >= maxVelocityX * -1) {
			this.velocityX = velocityX;
		} else {
			this.velocityX = maxVelocityX;
		}
	}

	/**
	 * Die Methode gibt aus, wie schnell sich das Objekt auf der y-Achse bewegen kann (Pixel pro Sekunde).
	 * 
	 * @return wie schnell sich das Objekt auf der y-Achse bewegen kann (Pixel pro Sekunde)
	 */
	public float getVelocityY() {
		return velocityY;
	}
	/**
	 * Die Methode ermöglicht es die aktuelle Geschwindigkeit auf der y-Achse des Objektes zu setzen.
	 * Maximal kann der Wert dabei +/- maxVelocityY annehmen!
	 * 
	 * @param velocityX die gewünschte aktuelle Geschwindigkeit des Objektes
	 */
	public void setVelocityY(float velocityY) {
		if (velocityY <= maxVelocityY  && velocityY >= maxVelocityY * -1) {
			this.velocityY = velocityY;
		} else {
			this.velocityY = maxVelocityY;
		}
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
