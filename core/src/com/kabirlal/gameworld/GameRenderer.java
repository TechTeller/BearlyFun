package com.kabirlal.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kabirlal.ui.UIManager;

public class GameRenderer
{
    private GameWorld world;
    private OrthographicCamera camera;
    private OrthographicCamera uiCamera;
    private SpriteBatch batcher;
    UIManager uiManager;
    ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world, Viewport viewport)
    {
        //World
        this.world = world;

        //Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        //Setup the UI
        uiManager = new UIManager(viewport);

        //Shape Renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(batcher, shapeRenderer);
        uiManager.render();
    }

    public void dispose()
    {
        batcher.dispose();
    }
}
