package examplegame;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.controller.animation.AnimationInterface;
import de.wenzel.paul.gameframework.util.SoundtrackManager;
import de.wenzel.paul.gameframework.view.GameRenderer;

/**
 * Die Klasse {@link GameActivity} ist die Activity des eigentlichen Spiels. Sie koordiniert den gesamten Ablauf.
 *
 * @author Paul Wenzel
 */
public class GameActivity extends Activity implements AnimationInterface, View.OnClickListener {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /**
     * das Model
     */
    private GameModel model;

    /**
     * die OpenGL SurfaceView auf die gezeichnet wird
     */
    private GLSurfaceView gameSurfaceView;
    /**
     * der Renderer, welcher auf die SurfaceView zeichnet und somit die View ist
     */
    private GameRenderer gameRenderer;

    private boolean continueMusic;

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Datenfelder initialisieren
        // View aufbauen
        setContentView(R.layout.game);

        final RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.gamelayout);
        gameSurfaceView = new GLSurfaceView(this);
        // SurfaceView dem Layout hinzufügen
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        gameLayout.addView(gameSurfaceView, glParams);

        // Alle View Elemente in den Vordergrund vor das Spielfeld hohlen
        for (View view : getAllChildren(gameLayout)) {
            if (view != gameSurfaceView) {
                view.bringToFront();
            }
        }

        model = new GameModel();

        gameRenderer = new GameRenderer(this, model, this);

        ////////////GameSurfaceView konfigurieren////////////
        // angeben, dass die Applikation für OpenGL 2.0 erstellt wird
        gameSurfaceView.setEGLContextClientVersion(2);
        // EGLConfigChooser setzen
        gameSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Renderer erstellen und dieser View zuweisen
        gameSurfaceView.setRenderer(gameRenderer);
        // der Renderer soll dauerhaft diese View neuzeichnen
        gameSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.setVisibility(View.GONE);

        if (!continueMusic) SoundtrackManager.pauseMediaPlayer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && gameSurfaceView.getVisibility() == View.GONE) {
            gameSurfaceView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameRenderer.onResume();

        SoundtrackManager.startMediaPlayer(this, R.raw.soundtrack1);
        continueMusic = false;
    }

    @Override
    public void animate(long loopDuration) {
//        if (model.isGameOver()) {
//            continueMusic = true;
//            Intent intent = new Intent(this, GameOverMenuActivity.class);
//            intent.putExtra(GameOverMenuActivity.INTENT_EXTRA_POINTS, model.getPlayer().getPoints());
//            intent.putExtra(GameOverMenuActivity.INTENT_EXTRA_WAVE, model.getCurrentWave());
//            startActivity(intent);
//        } else if (model.isWaveOver()) {
//            model.nextWave(new WaveBuilder(model.getCurrentWave() + 1).getAlienList());
//        } else {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    pointsTextView.setText("" + model.getPlayer().getPoints());
//
//                }
//            });
//
//            playerAnimation.animate(loopDuration);
//            alienAnimation.animate(loopDuration);
//            barricadeAnimation.animate(loopDuration);
//            shootAnimation.animate(loopDuration);
//            explosionAnimation.animate(loopDuration);
//        }
    }

    @Override
    public void onClick(View view) {
//        if (view == pauseButton) {
//            SoundEffectManager.playSoundEffect("button.mp3");
//            continueMusic = true;
//            Intent intent = new Intent(this, PauseMenuActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_MENU) {
//            continueMusic = true;
//            Intent intent = new Intent(this, PauseMenuActivity.class);
//            startActivity(intent);
//        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
//            continueMusic = true;
//            Intent intent = new Intent(this, PauseMenuActivity.class);
//            startActivity(intent);
//        }
//
//        return true;
        return false;
    }

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

    private ArrayList<View> getAllChildren(View view) {

        if (!(view instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(view);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(view);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	


}
