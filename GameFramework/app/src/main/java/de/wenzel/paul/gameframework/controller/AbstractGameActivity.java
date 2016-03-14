package de.wenzel.paul.gameframework.controller;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import de.wenzel.paul.gameframework.controller.animation.AnimationInterface;
import de.wenzel.paul.gameframework.model.AbstractGameModel;
import de.wenzel.paul.gameframework.view.GameRenderer;

/**
 * Die Klasse {@link AbstractGameActivity} ist die Activity des eigentlichen Spiels. Sie koordiniert den gesamten Ablauf.
 *
 * @author Paul Wenzel
 */
public abstract class AbstractGameActivity extends AbstractActivity implements AnimationInterface {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /**
     * das Model
     */
    private AbstractGameModel model;

    /**
     * die OpenGL SurfaceView auf die gezeichnet wird
     */
    private GLSurfaceView gameSurfaceView;
    /**
     * der Renderer, welcher auf die SurfaceView zeichnet und somit die View ist
     */
    private GameRenderer gameRenderer;

    private boolean continueMusic;

    /** die Ressource ID vom Layout, welches angezeigt werden soll */
    private int gameLayoutResId;
    /** die Ressource ID vom Relative Layout, welches sich im Game Layout befindet
     *  und in welche die OpenGL Surface View rein soll, auf welche wiederum gezeichnet wird
     */
    private int relativeLayoutForGameSurfaceView;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////

    /**
     * Konstruktor der Klasse {@link AbstractGameModel}.
     *
     * @param model das {@link AbstractGameModel}
     * @param gameLayoutResId die Ressource ID vom Layout, welches angezeigt werden soll
     * @param relativeLayoutForGameSurfaceView die Ressource ID vom Relative Layout, welches sich im Game Layout befindet
     *                                         und in welche die OpenGL Surface View rein soll, auf welche wiederum gezeichnet wird
     * @param soundTrackResourceID die Ressource ID von dem gewünschten Soundtrack (-1, wenn kein Soundtrack erwünscht)
     */
    public AbstractGameActivity(AbstractGameModel model, int gameLayoutResId, int relativeLayoutForGameSurfaceView, int soundTrackResourceID) {
        super(gameLayoutResId, soundTrackResourceID);

        this.model = model;
        this.gameLayoutResId = gameLayoutResId;
        this.relativeLayoutForGameSurfaceView = relativeLayoutForGameSurfaceView;
    }

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Datenfelder initialisieren
        final RelativeLayout gameLayout = (RelativeLayout) findViewById(relativeLayoutForGameSurfaceView);
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

        create();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.setVisibility(View.GONE);

        pause();
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

        resume();
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

//////////////////////////////////////////////////Abstrakte Methoden///////////////////////////////////////////////////

    /**
     * Die Methode generiert alle notwendigen neuen Entities.
     * (Da zum Beipiel der Spieler durch die Welt wandert und somit neue Objekte geladen werden müssen
     * oder der Spieler ein Level geschafft hat und das nächste geladen werden muss.)
     */
    protected abstract void createNewEntities();

    /**
     * Die Methode entfernt alle nicht mehr benötigten Entities aus dem Model.
     * (Da zum Beispiel der Spieler einen bestimmten Teil der Spielwelt nicht mehr betreten wird.)
     */
    protected abstract void removeOldEntities();

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////


}
