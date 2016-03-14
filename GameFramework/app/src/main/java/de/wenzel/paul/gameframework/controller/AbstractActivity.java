package de.wenzel.paul.gameframework.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import de.wenzel.paul.gameframework.util.SoundtrackManager;

/**
 * Die Klasse {@link AbstractActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class AbstractActivity extends Activity {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	/** die Ressource ID von dem gewünschten Soundtrack (-1, wenn kein Soundtrack erwünscht) */
	private int soundTrackResourceID;

	/** die Ressource ID von dem gewünschten Layout der Activity */
	private int layoutResourceID;

	/** die Variable ist dafür da, um zu wissen, ob die Musik einfach weiter laufen soll oder pausiert werden soll,
	 * wenn die onPause()-Methode der Activity aufgerufen wird. In Android ist es leider nicht möglich
	 * zu erfahren, ob der Home-Button gedrückt wurde, deswegen muss immer davon ausgegangen werden, dass
	 * wenn die onPause()-Methode gedrückt wurde, dies der Fall ist und somit die Musik pausiert werden muss.
	 * Man muss aber selber gucken, ob man selber dafür verantwortlich ist, dass die onPause()-Methode aufgerufen wurde
	 * (zum Beispiel weil 1) zu einer anderen Activity gewechelt wird 2) der Backbutton gedrückt wird)*/
	private boolean continueMusic;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////

	/**
	 * Der Konstruktor der Klasse {@link AbstractActivity}.
	 *
	 * @param layoutResourceID die Ressource ID vom Layout, welches angezeigt werden soll
	 * @param soundTrackResourceID die Ressource ID von dem gewünschten Soundtrack (-1, wenn kein Soundtrack erwünscht)
	 */
	public AbstractActivity(int layoutResourceID, int soundTrackResourceID) {
		// Datenfelder initialisieren
		this.soundTrackResourceID = soundTrackResourceID;
		this.layoutResourceID = layoutResourceID;
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutResourceID);
		create();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (soundTrackResourceID > -1) {
			SoundtrackManager.startMediaPlayer(this, soundTrackResourceID);
		}
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return menuButton();
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
            // wenn das Event nicht behandelt wird, dann ganz normal zur vorherigen Activity wechseln
            if (!backButton()) {
                onBackPressed();
            } else {
				return true;
			}
		}
		return false;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	@Override
	public void startActivity(Intent intent) {
		continueMusic = true;
		super.startActivity(intent);
	}

    @Override
    public void onBackPressed() {
        // wenn die Activity die letzte Activity meiner App ist, dann die Sountrackwiedergabe pausieren
        if (isTaskRoot()) {
            SoundtrackManager.pauseMediaPlayer();

        // andernfalls die Soundtrackwiedergabe fortsetzen
        } else {
            continueMusic = true;
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        // wenn die Activity die letzte Activity meiner App ist, dann die Sountrackwiedergabe pausieren
        if (isTaskRoot()) {
            SoundtrackManager.pauseMediaPlayer();

        // andernfalls die Soundtrackwiedergabe fortsetzen
        } else {
            continueMusic = true;
        }
        super.finish();
    }

    /////////////////////////////////////////////Abstrakte Methoden//////////////////////////////////////////////

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


	/**
	 * Die Methode wird aufgerufen, wenn der Menü-Button gedrückt wird.
	 *
	 * @return true = das Event wurde bandelt, false = das Event wurde noch nicht behandlet
	 */
	protected abstract boolean menuButton();

	/**
	 * Die Methode wird aufgerufen, wenn der Back-Button gedrückt wird.
	 *
	 * @return true = das Event wurde bandelt, false = das Event wurde noch nicht behandlet
	 */
	protected abstract boolean backButton();

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
