package examplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.util.SoundEffectManager;
import de.wenzel.paul.gameframework.util.SoundtrackManager;

/**
 * Die Klasse {@link StartMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class StartMenuActivity extends Activity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button playButton;
	private Button buildShipButton;
	private Button optionsButton;
	private Button creditsButton;
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link StartMenuActivity}. 
	 */
	public StartMenuActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_menu);
		
		// Datenfelder initialisieren
		playButton = (Button)findViewById(R.id.buttonPlayStartMenu);
		playButton.setOnClickListener(this);
		buildShipButton = (Button)findViewById(R.id.buttonBuildShipStartMenu);
		buildShipButton.setOnClickListener(this);
		optionsButton = (Button)findViewById(R.id.buttonOptionsStartMenu);
		optionsButton.setOnClickListener(this);
		creditsButton = (Button)findViewById(R.id.buttonCreditsStartMenu);
		creditsButton.setOnClickListener(this);
		
//		SoundEffectManager.loadSoundFiles(this, (AudioManager) getSystemService(Context.AUDIO_SERVICE));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SoundtrackManager.startMediaPlayer(this, R.raw.soundtrack1);
		continueMusic = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		if (!continueMusic) SoundtrackManager.pauseMediaPlayer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View view) {
		if (view == playButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, GameActivity.class);
			startActivity(intent);
		}
		if (view == optionsButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, OptionsMenuActivity.class);
			startActivity(intent);
		}
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
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
