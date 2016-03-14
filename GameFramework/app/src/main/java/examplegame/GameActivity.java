package examplegame;

import android.content.Intent;
import android.view.View;

import de.wenzel.paul.gameframework.R;
import de.wenzel.paul.gameframework.controller.AbstractGameActivity;

/**
 * Die Klasse {@link GameActivity} ist die Activity des eigentlichen Spiels. Sie koordiniert den gesamten Ablauf.
 *
 * @author Paul Wenzel
 */
public class GameActivity extends AbstractGameActivity implements View.OnClickListener {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////

    public GameActivity() {
        super(new GameModel(), R.layout.game, R.id.gamelayout, R.raw.soundtrack1);
    }

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

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
    protected void createNewEntities() {

    }

    @Override
    protected void removeOldEntities() {

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

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////


///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	


}
