package de.wenzel.paul.gameframework.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.KeyEvent;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.model.sharedpreferences.OptionsSharedPreferences;
import de.wenzel.paul.gameframework.util.SoundEffectManager;
import de.wenzel.paul.gameframework.util.SoundtrackManager;

/**
 * Die Klasse {@link AbstractMainActivity} von dieser Klasse sollte nur die MainActivity erben, die
 * als aller aller erstes aufgerufen wird, um alle benötigten Ressourcen zu laden (ist kein Screen,
 * mit welchem interagiert werden kann). Nach dem laden der Ressourcen verlinkt die Activity automatisch
 * weiter an die erste richtige Activity der App.
 *
 * @author Paul Wenzel
 */
public abstract class AbstractMainActivity extends Activity {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /** die Activity, welche nach dem laden der App als erstes geöffnet werden soll */
    private Class<?> startActivity;

    public static SoundtrackManager soundtrackManager;
    public static SoundEffectManager soundEffectManager;
    public static OptionsSharedPreferences optionsSharedPreferences;

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /**
     * Der Konstruktor der Klasse {@link AbstractMainActivity}.
     *
     * @param startActivity die Activity, welche nach dem laden der App als erstes geöffnet werden soll
     */
    public AbstractMainActivity(Class<?> startActivity) {
        this.startActivity = startActivity;
    }

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        // Alles gewünschte laden...
        HandlerThread thread = new HandlerThread("MyHandlerThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Datenfelder initialisieren
                optionsSharedPreferences = new OptionsSharedPreferences(getApplicationContext());
                soundtrackManager = new SoundtrackManager();
                soundEffectManager = new SoundEffectManager(getApplicationContext());

                // sonstige Dinge laden
                load();

                // StartActivity starten und die aktuelle für immer beenden
                Intent intent = new Intent(AbstractMainActivity.this, startActivity);
                AbstractMainActivity.this.startActivity(intent);
                AbstractMainActivity.this.finish();
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

/////////////////////////////////////////////Abstrakte Methoden//////////////////////////////////////////////

    /**
     * Die Methode definiert, was alles am anfang geladen werden soll, während ein Lade-Screen eingebelendet wird.
     */
    protected abstract void load();

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	


}
