package com.kabirlal.gameobjects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bear {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;

    private boolean grabbing;
    public Rectangle hitbox;

    public Bear(float x, float y, int width, int height)
    {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0, 5000);

        hitbox = new Rectangle(position.x, position.y, this.width, this.height);
    }

    public void update(int groundLevel, float delta)
    {
        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        if(position.y + height > groundLevel)
            position.y = groundLevel - height;

        hitbox.setPosition(position);
        hitbox.setHeight(height);
        hitbox.setWidth(width);
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.RED);
        renderer.rect(hitbox.x, hitbox.y, hitbox.getWidth(), hitbox.getHeight());
    }

    public void jump()
    {
        velocity.y = -2000;
    }

    public void doGrab()
    {
        grabbing = true;
    }

    public void undoGrab()
    {
        if(grabbing)
        {
            grabbing = false;
        }

    }

    public boolean isGrabbing()
    {
        return grabbing;
    }

    public void setGrabbing(boolean grabbing)
    {
        this.grabbing = grabbing;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getPosition()
    {
        return position;
    }
}
