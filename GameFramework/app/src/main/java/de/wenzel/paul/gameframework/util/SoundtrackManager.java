package de.wenzel.paul.gameframework.util;

import android.content.Context;
import android.media.MediaPlayer;

import de.wenzel.paul.gameframework.controller.MainActivity;
import de.wenzel.paul.gameframework.model.sharedpreferences.OptionsSharedPreferences;

/**
 * Die Klasse SoundtrackManager welche die Soundtracks verwaltet und Abspielt.
 * 
 * @author Paul Wenzel
 *
 */
public class SoundtrackManager implements OptionsSharedPreferences.OptionsSharedPreferencesListener {
	
/////////////////////////////////////////////Datenfelder deklarieren////////////////////////////////////////////

	private static MediaPlayer mediaPlayer = null;
	
	/** bis wohin der Track abgespielt wurde, als die Musik pausiert wurde,
	 * um sie hinterher an der selben Stelle wieder fortsezen zu kÃ¶nnen */
	private static int length = -1;

	private static int currentTrackResourceID = -1;
	
/////////////////////////////////////////////Konstruktor///////////////////////////////////////////////////////

	/**
	 * Konstruktor MediaPlayerSoundtrack 
	 */
	public SoundtrackManager() {
		//Datenfelder initialisieren
		MainActivity.optionsSharedPreferences.addOptionsSharedPreferencesListener(this);
	}

/////////////////////////////////////////////Getter und Setter////////////////////////////////////////////////

	
	
/////////////////////////////////////////////geerbte Methoden/////////////////////////////////////////////////

	@Override
	public void optionsSharedPreferencesChanged() {
		float soundtrackVolume = MainActivity.optionsSharedPreferences.getSoundtrackVolume();
		mediaPlayer.setVolume(soundtrackVolume, soundtrackVolume);
	}

/////////////////////////////////////////////Methoden////////////////////////////////////////////////////////

	/**
	 * Die Methode strtet die Wiedergabe des MediaPlayers.
	 * Wenn sie zuvor pausiert wurde wird die Wiedergabe an der gestopten stelle fortgesetzt.
	 * Falls er bereitspielt wird die Widergabe nach wie vor fortgesetzt.
	 * 
	 * @param context
	 * @param resourceID
	 */
	public static void startMediaPlayer(Context context, int resourceID) {
		if (MainActivity.optionsSharedPreferences.isSoundtrackOn()) {
			if (resourceID == currentTrackResourceID) {
				resumeMediaPlayer();
			} else {
				stopMediaPlayer();
				currentTrackResourceID = resourceID;
				mediaPlayer = MediaPlayer.create(context, resourceID);
				mediaPlayer.setLooping(true);
				float soundtrackVolume = MainActivity.optionsSharedPreferences.getSoundtrackVolume();
				mediaPlayer.setVolume(soundtrackVolume, soundtrackVolume);
				mediaPlayer.start();
			}
		}
	}
	
	/**
	 * Die Methode pausiert die wiedergabe des MediaPlayers. Wenn MediaPlayer nicht an ist bleibt er aus.
	 */
	public static void pauseMediaPlayer() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) { //gucken ob der MediaPlayer Ã¼berhaupt spielt
			mediaPlayer.pause();
			length = mediaPlayer.getCurrentPosition();
		}
	}
	
	/**
	 * Die Methode setzt die wiedergabe des MediaPlayers fort, an der stelle an welcher er pausiert wurde.
	 */
	private static void resumeMediaPlayer() {
		if (MainActivity.optionsSharedPreferences.isSoundtrackOn() && length != -1 && mediaPlayer != null) {
			mediaPlayer.seekTo(length);
			mediaPlayer.start();
			length = -1;
		}
	}
	
	/**
	 * Die Methode stopt die wiedergabe des MediaPlayers. Wenn MediaPlayer nicht an ist bleibt er aus.
	 */
	private static void stopMediaPlayer() {
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
			length = -1;
			currentTrackResourceID = -1;
		}
	}
}
