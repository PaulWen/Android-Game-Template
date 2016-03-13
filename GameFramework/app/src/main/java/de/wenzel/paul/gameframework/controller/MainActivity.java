package de.wenzel.paul.gameframework.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.model.sharedpreferences.OptionsSharedPreferences;
import de.wenzel.paul.gameframework.util.SoundEffectManager;
import de.wenzel.paul.gameframework.util.SoundtrackManager;
import examplegame.StartMenuActivity;

/**
 * Die Klasse {@link MainActivity} [...]
 *
 * @author Paul Wenzel
 */
public class MainActivity extends Activity {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    public static SoundtrackManager soundtrackManager;
    public static SoundEffectManager soundEffectManager;
    public static OptionsSharedPreferences optionsSharedPreferences;

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // Datenfelder initialisieren
                optionsSharedPreferences = new OptionsSharedPreferences(getApplicationContext());
                soundtrackManager = new SoundtrackManager();
                soundEffectManager = new SoundEffectManager(getApplicationContext());

                // StartActivity starten
                Intent intent = new Intent(MainActivity.this, StartMenuActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }

        return true;
    }

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	


}
