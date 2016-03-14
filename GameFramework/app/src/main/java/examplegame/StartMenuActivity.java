package examplegame;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.controller.AbstractActivity;
import de.wenzel.paul.gameframework.util.SoundEffectManager;

/**
 * Die Klasse {@link StartMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class StartMenuActivity extends AbstractActivity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button playButton;
	private Button buildShipButton;
	private Button optionsButton;
	private Button creditsButton;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link StartMenuActivity}. 
	 */
	public StartMenuActivity() {
		super(R.layout.start_menu, R.raw.soundtrack1);
	}

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    protected void create() {
        // Datenfelder initialisieren
        playButton = (Button)findViewById(R.id.buttonPlayStartMenu);
        playButton.setOnClickListener(this);
        buildShipButton = (Button)findViewById(R.id.buttonBuildShipStartMenu);
        buildShipButton.setOnClickListener(this);
        optionsButton = (Button)findViewById(R.id.buttonOptionsStartMenu);
        optionsButton.setOnClickListener(this);
        creditsButton = (Button)findViewById(R.id.buttonCreditsStartMenu);
        creditsButton.setOnClickListener(this);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected boolean menuButton() {
        Intent intent = new Intent(this, OptionsMenuActivity.class);
        startActivity(intent);

        return true;
    }

    @Override
    protected boolean backButton() {
        return false;
    }

    @Override
	public void onClick(View view) {
		if (view == playButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			Intent intent = new Intent(this, GameActivity.class);
			startActivity(intent);
		}
		if (view == optionsButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			Intent intent = new Intent(this, OptionsMenuActivity.class);
            startActivity(intent);
		}
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
