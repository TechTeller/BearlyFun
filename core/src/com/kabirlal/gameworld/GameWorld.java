package com.kabirlal.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.kabirlal.gameobjects.Bear;
import com.kabirlal.gameobjects.Hive;
import com.kabirlal.gameobjects.Tree;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.LevelManager;
import com.kabirlal.helpers.ScoreManager;
import com.kabirlal.screens.GameScreen;

public class GameWorld
{

    private Bear bear;
    private Tree tree;
    private Hive hive;

    private int groundLevel;
    private boolean hasEnded;

    private Vector2 bgPos;
    private Vector2 bg2Pos;
    private float speed;

    private LevelManager levelManager;

    public GameWorld()
    {
        speed = 10;
        hasEnded = false;
        groundLevel = Gdx.graphics.getHeight() - 20;

        bear = new Bear(200, 200, 200, 150 );
        tree = new Tree(1080, 345, 20);
        hive = new Hive(tree);
        levelManager = new LevelManager();

        bgPos = new Vector2(0, 0);
        bg2Pos = new Vector2(1920, 0);
    }

    public void update(float delta, GameScreen.GameState currentState)
    {
        switch (currentState)
        {
            case MENU:
                updateMenu(delta);
                break;
            case RUNNING:
                updateGame(delta, currentState);
                break;
            case GAMEOVER:
                updateGameOver(delta);
            default:
                return;
        }
    }

    public void updateGame(float delta, GameScreen.GameState currentState)
    {
        tree.update(delta, GameScreen.GameState.RUNNING);
        hive.update(delta);
        bear.update(groundLevel, delta);
        ScoreManager.updateScore(this);
        levelManager.update(this);
        updateBackground();
    }

    public void updateMenu(float delta)
    {
        //Play background music
        AssetLoader.bgMusic.play();

        //Update the tree
        tree.update(delta, GameScreen.GameState.MENU);
        //Update the hive on the tree
        hive.update(delta);
        bear.update(groundLevel, delta);
        //Update the bear
        bear.update(groundLevel, delta);
    }

    public void renderMenu(SpriteBatch batch, ShapeRenderer shape)
    {
        batch.begin();

        //Render the base background for the game
        //It does not start moving until currentState changes to RUNNING - Tap to start
        batch.draw(AssetLoader.bg, 0, 0);


        //Render the title logo in the middle of the screen
        batch.draw(AssetLoader.titleLogoRegion, Gdx.graphics.getWidth()/2 - AssetLoader.titleLogo.getWidth() / 2, 200);

        //Tree should be static with one hive on it
        tree.renderGraphic(batch);

        //Hive should be on the tree on a random location
        hive.renderGraphic(batch);

        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        //Render the bear in death animation
        bear.render(shape);
        shape.end();

    }

    public void renderGameOver(SpriteBatch batch)
    {
        batch.begin();

        //Draw the background
        batch.draw(AssetLoader.bg, 0, 0);

        //Draw new tree
        tree.renderGraphic(batch);

        //Draw the hive on the tree
        hive.renderGraphic(batch);


        batch.end();
    }

    public void updateGameOver(float delta)
    {
        ScoreManager.updateHighScore();
    }

    public Bear getBear()
    {
        return bear;
    }

    public void dispose()
    {

    }

    public void render(SpriteBatch batch, ShapeRenderer shape, GameScreen.GameState currentState)
    {
        switch (currentState)
        {
            case MENU:
                renderMenu(batch, shape);
                //UI Manager handles menu UI
                break;
            case RUNNING:
                renderGame(batch, shape);
                break;
            case GAMEOVER:
                renderGameOver(batch);
                //UI Manager handles gameover UI
                break;
            default:
                System.out.println("Unhandled game state encountered!" + currentState.toString());
        }
    }

    public void renderGame(SpriteBatch batch, ShapeRenderer shape)
    {
        batch.begin();
        renderBackground(batch);
        tree.renderGraphic(batch);
        hive.renderGraphic(batch);
        ScoreManager.renderMissedHives(batch);
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        //tree.renderHitbox(shape);
        //hive.renderHitbox(shape);
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
            bgPos.x = bg2Pos.x + AssetLoader.bg2.getRegionWidth();

        if(bg2Pos.x + AssetLoader.bg2.getRegionWidth() <= 0)
            bg2Pos.x = AssetLoader.bg2.getRegionWidth();

        bgPos.x = bgPos.x - speed;
        bg2Pos.x = bg2Pos.x - speed;
    }

    public void setSpeed(int speed)
    {
        tree.setSpeed(speed);
    }

    public void setHasEnded(boolean hasEnded)
    {
        this.hasEnded = hasEnded;
    }

    public boolean hasGameEnded()
    {
        return hasEnded;
    }

    public void reset()
    {
        LevelManager.startTime = TimeUtils.millis();
        this.setSpeed(10);
        tree.setSpeed(20);
        tree.setPosition(Gdx.graphics.getWidth(), 345);
        bear.setGrabbing(false);
        ScoreManager.score = 0;
        ScoreManager.hivesMissed = 0;
        ScoreManager.missedHives.clear();
        ScoreManager.scoredHives.clear();
    }

    public Hive getHive()
    {
        return hive;
    }
}
