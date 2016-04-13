package com.kabirlal.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.kabirlal.gameobjects.Bear;
import com.kabirlal.gameobjects.Hive;
import com.kabirlal.gameobjects.Tree;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.LevelManager;
import com.kabirlal.helpers.ScoreManager;

public class GameWorld
{
    private Bear bear;
    private Tree tree;
    private Hive hive;

    private int groundLevel;

    private Vector2 bgPos;
    private Vector2 bg2Pos;
    private float speed  = 10;

    private LevelManager levelManager;

    public GameWorld()
    {
        groundLevel = Gdx.graphics.getHeight() - 20;

        bear = new Bear(200, 200, 150, 150 );
        tree = new Tree(Gdx.graphics.getWidth(), 345, 100, 900, 20);
        hive = new Hive(tree);
        levelManager = new LevelManager();

        bgPos = new Vector2(0, 0);
        bg2Pos = new Vector2(1920, 0);
    }

    public void update(float delta)
    {
        tree.update(delta);
        hive.update(delta);
        bear.update(groundLevel, delta);
        ScoreManager.updateScore(bear, hive);
        levelManager.update(this);
        updateBackground();
    }

    public Bear getBear()
    {
        return bear;
    }

    public void dispose()
    {

    }

    public void render(SpriteBatch batch, ShapeRenderer shape)
    {
        batch.begin();
        renderBackground(batch);
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        tree.render(shape);
        hive.render(shape);
        bear.render(shape);
        shape.end();
    }

    public void renderBackground(SpriteBatch batch)
    {
        batch.draw(AssetLoader.bg, bgPos.x, bgPos.y);
        batch.draw(AssetLoader.bg2, bg2Pos.x, bg2Pos.y);
    }

    public void updateBackground()
    {
        if(bgPos.x + AssetLoader.bg.getRegionWidth() <= 0)
            bgPos.x = AssetLoader.bg.getRegionWidth() - 100;

        if(bg2Pos.x + AssetLoader.bg2.getRegionWidth() <= 0)
            bg2Pos.x = AssetLoader.bg2.getRegionWidth() - 100;

        bgPos.x = bgPos.x - speed;
        bg2Pos.x = bg2Pos.x - speed;
    }

    public void setSpeed(int speed)
    {
        tree.setSpeed(speed);
        this.speed = speed / 2;
    }
}
