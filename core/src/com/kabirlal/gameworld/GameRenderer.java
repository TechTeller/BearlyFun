package com.kabirlal.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameRenderer
{
    private GameWorld world;
    private OrthographicCamera camera;
    private SpriteBatch batcher;
    ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world)
    {
        //World
        this.world = world;

        //Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(true);


        //Shape Renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        batcher = new SpriteBatch();
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render(batcher, shapeRenderer);
    }

    public void dispose()
    {
        batcher.dispose();
    }
}
