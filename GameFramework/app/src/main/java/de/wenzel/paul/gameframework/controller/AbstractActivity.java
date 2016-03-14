package de.wenzel.paul.gameframework.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.util.SoundtrackManager;
import examplegame.OptionsMenuActivity;

/**
 * Die Klasse {@link AbstractActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class AbstractActivity extends Activity {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	private boolean continueMusic;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////

	/**
	 * Der Konstruktor der Klasse {@link AbstractActivity}.
	 */
	public AbstractActivity() {
		// Datenfelder initialisieren
		
	}

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_menu);
		
		create();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SoundtrackManager.startMediaPlayer(this, R.raw.soundtrack1);
		continueMusic = false;

		resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		if (!continueMusic) SoundtrackManager.pauseMediaPlayer();

		pause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			continueMusic = true;
			Intent intent = new Intent(this, OptionsMenuActivity.class);
			startActivity(intent);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
		}
		
		return true;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


/////////////////////////////////////////////Abstracte Methoden//////////////////////////////////////////////

	/**
	 * Die Methode wird am Ende der eigentlichen onCreate-Methode der Activity aufgerufen.
	 */
	protected abstract void create();
	/**
	 * Die Methode wird am Ende der eigentlichen onPause-Methode der Activity aufgerufen.
	 */
	protected abstract void pause();
	/**
	 * Die Methode wird am Ende der eigentlichen onResume-Methode der Activity aufgerufen.
	 */
	protected abstract void resume();

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
