package examplegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.controller.MainActivity;
import de.wenzel.paul.gameframework.util.SoundEffectManager;
import de.wenzel.paul.gameframework.util.SoundtrackManager;

/**
 * Die Klasse {@link OptionsMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class OptionsMenuActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button backButton;
	private SeekBar soundtrackVolumeSeekBar;
	private SeekBar soundEffectsVolumeSeekBar;
	
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link OptionsMenuActivity}. 
	 */
	public OptionsMenuActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_menu);
		
		// Datenfelder initialisieren
		backButton = (Button)findViewById(R.id.buttonBackOptionsMenu);
		backButton.setOnClickListener(this);
		soundtrackVolumeSeekBar = (SeekBar)findViewById(R.id.seekBarSoundtrackOptionsMenu);
		soundtrackVolumeSeekBar.setOnSeekBarChangeListener(this);
		soundtrackVolumeSeekBar.setProgress((int)(MainActivity.optionsSharedPreferences.getSoundtrackVolume() * 100));
		soundEffectsVolumeSeekBar = (SeekBar)findViewById(R.id.seekBarSoundEffectsOptionsMenu);
		soundEffectsVolumeSeekBar.setOnSeekBarChangeListener(this);
		soundEffectsVolumeSeekBar.setProgress((int)(MainActivity.optionsSharedPreferences.getSoundEffectsVolume() * 100));
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
		if (view == backButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			finish();
		}
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) {
			if (seekBar == soundEffectsVolumeSeekBar) {
				MainActivity.optionsSharedPreferences.setSoundEffectsVolume(progress / 100f);
			} else if (seekBar == soundtrackVolumeSeekBar) {
				MainActivity.optionsSharedPreferences.setSoundtrackVolume(progress / 100f);
			}
		}
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (seekBar == soundEffectsVolumeSeekBar) {
			SoundEffectManager.playSoundEffect("button.mp3");
		} 
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {

		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			continueMusic = true;
			finish();
		}
		
		return true;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
