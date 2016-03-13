package de.wenzel.paul.gameframework.controller.animation;


import java.util.ArrayList;

import de.wenzel.paul.gameframework.model.entities.TexturedOpenGlObject;
import de.wenzel.paul.gameframework.model.entities.skills.DestroyableSkill;
import de.wenzel.paul.gameframework.model.entities.skills.MoveableSkillInterface;
import de.wenzel.paul.gameframework.util.Rectangle;


/**
 * Die abstrakte Klasse {@link Animation} entählt Hilfsmethoden für Animationsklassen.
 * Jede Animationsklasse soll von diessr Klasse erben!
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class Animation implements AnimationInterface {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	
	
	
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der abstrakten Klasse {@link Animation}. 
	 */
	public Animation() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Methode, welche berechnet, um wie viele Pixel sich das Alien bewegen muss und es demnach bewegt.
	 * Außerdem gibt die Methode noch aus, ob das Objekt gegen eine Grenze geflogen ist.
	 * 
	 * @param entity für welches Objekt die Bewegung berechnet werden soll und angewandt werden soll
	 * @param loopDuration wie lange es her ist, dass die Position geupdatet wurde
	 * @param entityPolygon das Rechteck, welches das Objekt mit seiner aktuellen Position darstellt
 	 * @param boundaryList die Grenzen, welche als Rechtecke dargestellt sind - innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden
 	 * 
 	 * @return true: das Objekt ist gegen eine Grenze gestoßen
	 */
	protected boolean updateMovement(float loopDuration, MoveableSkillInterface entity, Rectangle entityPolygon, ArrayList<Rectangle> boundaryList) {
		boolean touchedBoundary = false;
		
		// Bewegung berechnen
		float xMovement = entity.getMovableSkill().getVelocityX() * (loopDuration / 1000f);
		float yMovement = entity.getMovableSkill().getVelocityY() * (loopDuration / 1000f);
		
		// neue Position auf ihre Gültigkeit hin überprüfen
		for (Rectangle boundary: boundaryList) {
			// das xMovement überprüfen
			if (xMovement != 0) {
				// die Bewegung anwenden 
				Rectangle newEntityPlolygon = new Rectangle(entityPolygon.getX() + xMovement, entityPolygon.getY(), entityPolygon.getWidth(), entityPolygon.getHeight());
				
				// gucken ob die Bewegung erlaubt ist
				if (boundary.intersects(newEntityPlolygon)) {
					// wenn die Bewegung nicht erlaubt ist, die Bewegung korrigieren
					if (xMovement > 0) {
						xMovement = xMovement - (newEntityPlolygon.right() - boundary.left());
						touchedBoundary = true;
					} else {
						xMovement = xMovement + (boundary.right() - newEntityPlolygon.left());
						touchedBoundary = true;
					}
				}
			}
			// das yMovement überprüfen
			if (yMovement != 0) {
				// die Bewegung anwenden 
				Rectangle newEntityPlolygon = new Rectangle(entityPolygon.getX(), entityPolygon.getY() + yMovement, entityPolygon.getWidth(), entityPolygon.getHeight());
				
				// gucken ob die Bewegung erlaubt ist
				if (boundary.intersects(newEntityPlolygon)) {
					// wenn die Bewegung nicht erlaubt ist, die Bewegung korrigieren
					if (yMovement > 0) {
						yMovement = yMovement - (newEntityPlolygon.top() - boundary.bottom());
						touchedBoundary = true;
					} else {
						yMovement = yMovement + (boundary.top() - newEntityPlolygon.bottom());
						touchedBoundary = true;
					}
				}
			}
		}
		
		// Position updaten
		entity.addMovement(xMovement, yMovement);
		
		return touchedBoundary;
	}
	
	/**
	 * Die Methode setzt der Anzahl der Leben entsprechend die gewünschte Textur eines Objektes.
	 * 
	 * @param entity das Objekt, welches upgedatet werden soll
	 * @param entitiyDestroyableSkill der {@link DestroyableSkill} des Objektes, welches upgedatet werden soll
	 */
	protected void updateTextureLive(TexturedOpenGlObject entity, DestroyableSkill entitiyDestroyableSkill) {
		/** alle wieviele Leben ein neuer Frame gezeigt werden soll */
		double livesPerFrame = (double)entitiyDestroyableSkill.getMaxLives() / (double)entity.getNumberOfSprites();
		
		// gucken ob bereits der nächste Frame gezeigt werden soll
		while ((int)((entitiyDestroyableSkill.getMaxLives() - entitiyDestroyableSkill.getLives()) / livesPerFrame) != entity.getCurrentSpriteIndexNumber()) {
			entity.nextSprite();
		}
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
