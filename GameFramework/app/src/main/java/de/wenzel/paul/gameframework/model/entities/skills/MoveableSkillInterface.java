package de.wenzel.paul.gameframework.model.entities.skills;




/**
 * Das Interface {@link MoveableSkillInterface} enthält alle Methoden, um ein bewegbares Objekt zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface MoveableSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////



//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt den Skill {@link MoveableSkill} aus.
	 * 
	 * @return den Skill {@link MoveableSkill}
	 */
	public MoveableSkill getMovableSkill();
	
	/**
	 * Die Methode ist dafür da, um das Objekt zu bewegen.
	 * 
	 * @param xMovement um wie viele Pixel sich das Objekt auf der x-Achse bewegen soll
	 * @param yMovement um wie viele Pixel sich das Objekt auf der y-Achse bewegen soll
	 */
	public void addMovement(float xMovement, float yMovement);
}

