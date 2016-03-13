package de.wenzel.paul.gameframework.model.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import java.util.ArrayList;

/**
 * Die Klasse {@link OptionsSharedPreferences} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class OptionsSharedPreferences implements OnSharedPreferenceChangeListener {
	
/////////////////////////////////////////////////Konstanten//////////////////////////////////////////////////
	
	private static final String OPTIONS_FILE_NAME = "optionsFile";
	private static final String SOUNDTRACK_VOLUME = "soundtrackVolume";
	private static final float SOUNDTRACK_VOLUME_DEFAULT = 1f;
	private static final String SOUND_EFFECTS_VOLUME = "soundEffectsVolume";
	private static final float SOUND_EFFECTS_VOLUME_DEFAULT = 0.5f;
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** Liste aller registrierten Listener **/
	private ArrayList<OptionsSharedPreferencesListener> optionsSharedPreferencesListenerList;
	private SharedPreferences sharedPreferences;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link OptionsSharedPreferences}. 
	 */
	public OptionsSharedPreferences(Context context) {
		// Datenfelder initialisieren
		optionsSharedPreferencesListenerList = new ArrayList<OptionsSharedPreferencesListener>();
		sharedPreferences = context.getSharedPreferences(OPTIONS_FILE_NAME, Context.MODE_PRIVATE);
		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		informOptionsSharedPreferencesListeners();
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode dient dem registrieren eines Listeners.
	 * 
	 * @param listener der Listener, welcher registriert werden soll
	 */
	public void addOptionsSharedPreferencesListener(OptionsSharedPreferencesListener listener) {
		//den Listener als Listener von dieser View eintragen
		optionsSharedPreferencesListenerList.add(listener);
	}
	
	/**
	 * Die Methode dient dem entfernen eines Listeners.
	 * 
	 * @param listener der Listener, welcher entfernt werden soll
	 */
	public void removeOptionsSharedPreferencesListener(OptionsSharedPreferencesListener listener) {
		//den Listener als Listener von dieser View entfernen
		optionsSharedPreferencesListenerList.remove(listener);
	}
	
	/**
	 * Diese Methode dient zum Benachrichtiogen aller registrierten Listener.
	 */
	public void informOptionsSharedPreferencesListeners() {
		for (OptionsSharedPreferencesListener optionsSharedPreferencesListener : optionsSharedPreferencesListenerList) {
			optionsSharedPreferencesListener.optionsSharedPreferencesChanged();
		}		
	}
	
	
	
	
	/**
	 * Die Methode dient dazu die Soundtrack-Lautstärke abzuspeichern.
	 * 
	 * @param soundtrackVolume die Soundtrack-Lautstärke die gespeichert werden soll
	 */
	public void setSoundtrackVolume(float soundtrackVolume) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putFloat(SOUNDTRACK_VOLUME, soundtrackVolume);
		editor.commit();
	}
	
	/**
	 * Die Methode dient dazu die Soundtrack-Lautstärke auszulesen.
	 * 
	 * @return die Soundtrack-Lautstärke oder -1, wenn ein Fehler aufgetreten ist
	 */
	public float getSoundtrackVolume() {
		return sharedPreferences.getFloat(SOUNDTRACK_VOLUME, SOUNDTRACK_VOLUME_DEFAULT);
	}
	
	/**
	 * Die Methode gibt aus, ob der Soundtrack an ist.
	 * 
	 * @return true, wenn der Soundtrack an ist
	 */
	public boolean isSoundtrackOn() {
		return sharedPreferences.getFloat(SOUNDTRACK_VOLUME, SOUNDTRACK_VOLUME_DEFAULT) > 0;
	}
	
	/**
	 * Die Methode dient dazu die Sound-Effect-Lautstärke abzuspeichern.
	 * 
	 * @param soundEffectsVolume die Sound-Effect-Lautstärke die gespeichert werden soll
	 */
	public void setSoundEffectsVolume(float soundEffectsVolume) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putFloat(SOUND_EFFECTS_VOLUME, soundEffectsVolume);
		editor.commit();
	}
	
	/**
	 * Die Methode dient dazu die Sound-Effect-Lautstärke auszulesen.
	 * 
	 * @return die Sound-Effect-Lautstärke oder -1, wenn ein Fehler aufgetreten ist
	 */
	public float getSoundEffectsVolume() {
		return sharedPreferences.getFloat(SOUND_EFFECTS_VOLUME, SOUND_EFFECTS_VOLUME_DEFAULT);
	}
	
	/**
	 * Die Methode gibt aus, ob die Sound-Effekte an sind.
	 * 
	 * @return true, wenn die Sound-Effekte an sind
	 */
	public boolean isSoundEffectsOn() {
		return sharedPreferences.getFloat(SOUND_EFFECTS_VOLUME, SOUND_EFFECTS_VOLUME_DEFAULT) > 0;
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	/**
	 * Die inneres Interface {@link OptionsSharedPreferencesListener} [...]
	 * 
	 * 
	 * @author Paul Wenzel
	 *
	 */
	public interface OptionsSharedPreferencesListener {
		
	///////////////////////////////////////////////Datenfelder///////////////////////////////////////////////
		
		
		
		
	////////////////////////////////////////////////Methoden/////////////////////////////////////////////////
	
		public void optionsSharedPreferencesChanged();
	
	}

}
