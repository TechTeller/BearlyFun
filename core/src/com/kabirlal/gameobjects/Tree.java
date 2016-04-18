package com.kabirlal.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.screens.GameScreen;

public class Tree
{
    private Vector2 position;
    private float height;
    private float width;
    private Rectangle hitbox;
    private float speed ;

    public Tree(float x, float y, float speed)
    {
        this.position = new Vector2(x, y);
        this.speed = speed;
        this.height = AssetLoader.treeRegion.getRegionHeight();
        this.width = AssetLoader.treeRegion.getRegionWidth();

        hitbox = new Rectangle(position.x, position.y, this.width, this.height);
    }

    public void update(float delta, GameScreen.GameState currentState)
    {
        if(currentState == GameScreen.GameState.MENU)
        {
            //Do nothing, just render the tree
        }
        else if(currentState == GameScreen.GameState.RUNNING)
        {
            if(position.x < 0 - width)
                position.x = Gdx.graphics.getWidth() + width;
            position.x = position.x - speed;
        }
    }

    public void renderHitbox(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BROWN);
        renderer.rect(hitbox.x, hitbox.y, hitbox.getWidth(), hitbox.getHeight());
    }

    public void renderGraphic(SpriteBatch batch)
    {
        batch.draw(AssetLoader.treeRegion, position.x, position.y);
    }

    public float getHeight()
    {
        return height;
    }

    public float getWidth()
    {
        return width;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;
    }
}
