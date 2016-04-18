package com.kabirlal.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kabirlal.gameworld.GameRenderer;
import com.kabirlal.gameworld.GameWorld;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.InputHandler;
import com.kabirlal.ui.UIManager;

public class GameScreen implements Screen
{
    public enum GameState
    {
        MENU, RUNNING, PAUSED, GAMEOVER
    }

    private GameState currentState;

    String TAG = "BearlyFun";

    private GameWorld world;
    private GameRenderer renderer;


    Viewport uiViewport;
    OrthographicCamera camera;

    UIManager uiManager;

    private float runTime = 0;

    public float screenWidth = 1920;
    public float screenHeight = 1080;

    public GameScreen()
    {
        currentState = GameState.MENU;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        uiViewport = new FitViewport(screenWidth, screenHeight, camera);

        world = new GameWorld();
        renderer = new GameRenderer(world, currentState);

        Gdx.input.setInputProcessor(new InputHandler(this));

        //Setup the UI
        uiManager = new UIManager(uiViewport, this);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show method called!");
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        if(world.hasGameEnded())
        {
            world.setHasEnded(false);
            setCurrentState(GameState.GAMEOVER);
        }

        world.update(delta, currentState);

        renderer.render(currentState);

        uiManager.update(currentState);
    }

    @Override
    public void resize(int width, int height)
    {
        uiViewport.update(width, height);
        uiManager.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
        AssetLoader.dispose();
    }

    public GameState getCurrentState()
    {
        return currentState;
    }

    public void  setCurrentState(GameState currentState)
    {
        this.currentState = currentState;
    }

    public GameWorld getWorld()
    {
        return world;
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }


}
