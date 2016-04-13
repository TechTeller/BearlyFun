package com.kabirlal.barelyfun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kabirlal.gameworld.GameRenderer;
import com.kabirlal.gameworld.GameWorld;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.InputHandler;

public class GameScreen implements Screen
{
    String TAG = "BearlyFun";
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen()
    {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / ( screenWidth / gameWidth );
        int midPointY = (int) (gameHeight / 2);


        AssetLoader.load();

        world = new GameWorld();
        renderer = new GameRenderer(world);

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
