package com.kabirlal.barelyfun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kabirlal.gameworld.GameRenderer;
import com.kabirlal.gameworld.GameWorld;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.InputHandler;

public class GameScreen implements Screen
{
    String TAG = "BearlyFun";

    private GameWorld world;
    private GameRenderer renderer;

    Viewport viewport;
    OrthographicCamera camera;

    private float runTime = 0;


    public GameScreen()
    {
        float screenWidth = 1920;
        float screenHeight = 1080;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        viewport = new FitViewport(screenWidth, screenHeight);

        float gameWidth = 136;
        float gameHeight = screenHeight / ( screenWidth / gameWidth );
        int midPointY = (int) (gameHeight / 2);


        AssetLoader.load();

        world = new GameWorld();
        renderer = new GameRenderer(world, viewport);

        Gdx.input.setInputProcessor(new InputHandler(world.getBear()));
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show method called!");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);

        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
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

    }
}
