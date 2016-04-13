package com.kabirlal.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tree
{
    private Vector2 position;
    private float height;
    private float width;
    private Rectangle hitbox;
    private float speed ;

    public Tree(float x, float y, int width, int height, float speed)
    {
        this.position = new Vector2(x, y);
        this.speed = speed;
        this.height = height;
        this.width = width;

        hitbox = new Rectangle(position.x, position.y, this.width, this.height);
    }

    public void update(float delta)
    {
        if(position.x < 0 - width)
            position.x = Gdx.graphics.getWidth() + width;
        position.x = position.x - speed;
        hitbox.setPosition(position);
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BROWN);
        renderer.rect(hitbox.x, hitbox.y, hitbox.getWidth(), hitbox.getHeight());
    }

    public float getBaseHeight()
    {
        return position.y + height;
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
