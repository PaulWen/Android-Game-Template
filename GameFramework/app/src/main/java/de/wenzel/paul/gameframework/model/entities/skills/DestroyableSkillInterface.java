package de.wenzel.paul.gameframework.model.entities.skills;


import de.wenzel.paul.gameframework.util.Rectangle;

/**
 * Das Interface {@link DestroyableSkillInterface} enthält alle Methoden, um ein zerstörbares Objekt zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface DestroyableSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt den Skill {@link DestroyableSkill} aus.
	 * 
	 * @return den Skill {@link DestroyableSkill}
	 */
	public DestroyableSkill getDestroyableSkill();
	
	/**
	 * Die Methode gibt ein {@link Rectangle} aus, welches das Objekt repräsentiert.
	 * Das {@link Rectangle} kann zum Beispiel zu Kollisionsüberprüfung verwendet werden.
	 * 
	 * @return ein {@link Rectangle}, welches das Objekt repräsentiert
	 */
	public Rectangle getPolygon();
}

