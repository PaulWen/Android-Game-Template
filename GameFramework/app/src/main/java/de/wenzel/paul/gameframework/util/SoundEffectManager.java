package de.wenzel.paul.gameframework.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import de.wenzel.paul.gameframework.Config;
import de.wenzel.paul.gameframework.controller.MainActivity;


/**
 * Die Klasse SoundPoolManager dient zum Abspielen von SoundEffects. Als erstes lädt der SoundPool diese,
 * um sie während des Spiels dann auf befehl apspielen zukönnen.
 * 
 * @author Paul Wenzel
 *
 */
public class SoundEffectManager {
	
/////////////////////////////////////////////Datenfelder deklarieren////////////////////////////////////////////

	private static SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0); // SoundPool zum abspielen und Laden von Sounds
	private static HashMap<String, Integer> soundList = new HashMap<String, Integer>(); // Liste mit allen Ids der geladenen Sounds um auf sie über den SoundPool abzuspielen
	
/////////////////////////////////////////////Konstruktor///////////////////////////////////////////////////////

	/**
	 * Konstruktor SoundPoolManager
	 * 
	 * @param context => zum laden der Soundeffekte
	 */
	public SoundEffectManager(Context context) {
		//Datenfelder initialisieren
		try {
			String [] fileNameList = context.getAssets().list(Config.SOUND_EFFECTS_FOLDER);
			for (String fileName : fileNameList) {
				soundList.put(fileName, soundPool.load(context.getAssets().openFd(Config.SOUND_EFFECTS_FOLDER + "/" + fileName), 1));
			}
		} catch (IOException e) {
			if (LoggerConfig.ERROR) Log.e(SoundEffectManager.class + "", e.toString());
		}
	}

/////////////////////////////////////////////Getter und Setter////////////////////////////////////////////////



///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

	
/////////////////////////////////////////////Methoden////////////////////////////////////////////////////////
	
	/**
	 * Methode, welche einen Sound abspielt
	 * 
	 * @param fileName => der Name des Soundes, wie er ind der SoundList zufinden ist
	 */
	public static void playSoundEffect(String fileName) {
		if (MainActivity.optionsSharedPreferences.isSoundEffectsOn()) { //nur wenn Soundeffecte in den Optionen an sind
			float soundEffectVolume = MainActivity.optionsSharedPreferences.getSoundEffectsVolume();
			soundPool.play(soundList.get(fileName), soundEffectVolume, soundEffectVolume, 1, 0, 0);
		}
	}

}
