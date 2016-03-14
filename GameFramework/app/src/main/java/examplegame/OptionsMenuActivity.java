package examplegame;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.controller.AbstractActivity;
import de.wenzel.paul.gameframework.controller.AbstractMainActivity;
import de.wenzel.paul.gameframework.util.SoundEffectManager;

/**
 * Die Klasse {@link OptionsMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class OptionsMenuActivity extends AbstractActivity implements OnClickListener, OnSeekBarChangeListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button backButton;
	private SeekBar soundtrackVolumeSeekBar;
	private SeekBar soundEffectsVolumeSeekBar;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link OptionsMenuActivity}. 
	 */
	public OptionsMenuActivity() {
		super(R.layout.options_menu, R.raw.soundtrack1);
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Datenfelder initialisieren
		backButton = (Button)findViewById(R.id.buttonBackOptionsMenu);
		backButton.setOnClickListener(this);
		soundtrackVolumeSeekBar = (SeekBar)findViewById(R.id.seekBarSoundtrackOptionsMenu);
		soundtrackVolumeSeekBar.setOnSeekBarChangeListener(this);
		soundtrackVolumeSeekBar.setProgress((int)(AbstractMainActivity.optionsSharedPreferences.getSoundtrackVolume() * 100));
		soundEffectsVolumeSeekBar = (SeekBar)findViewById(R.id.seekBarSoundEffectsOptionsMenu);
		soundEffectsVolumeSeekBar.setOnSeekBarChangeListener(this);
		soundEffectsVolumeSeekBar.setProgress((int)(AbstractMainActivity.optionsSharedPreferences.getSoundEffectsVolume() * 100));
	}
	
	@Override
	public void onClick(View view) {
		if (view == backButton) {
			finish();
		}
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) {
			if (seekBar == soundEffectsVolumeSeekBar) {
				AbstractMainActivity.optionsSharedPreferences.setSoundEffectsVolume(progress / 100f);
			} else if (seekBar == soundtrackVolumeSeekBar) {
				AbstractMainActivity.optionsSharedPreferences.setSoundtrackVolume(progress / 100f);
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
	protected void create() {

	}

	@Override
	protected void pause() {

	}

	@Override
	protected void resume() {

	}

	@Override
	protected boolean menuButton() {
		return false;
	}

	@Override
	protected boolean backButton() {
		finish();

		return true;
	}

	//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
