package com.kabirlal.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hive
{
    float level;
    Tree tree;
    Bear bear;
    Vector2 position;
    public Rectangle hitbox;
    private int id = -1;

    float height, width;

    public Hive(Tree tree)
    {
        this.tree = tree;
        spawn();
        position = new Vector2(tree.getPosition().x, level);
        height = width = tree.getWidth();
        hitbox = new Rectangle(position.x, position.y, width, height);
    }

    public void spawn()
    {
        id++;
        level = MathUtils.random(tree.getPosition().y, 900);
    }

    public void update(float delta)
    {
        if(position.x + tree.getWidth() < 0)
            spawn();
        position.y = level;
        position.x = tree.getPosition().x;
        hitbox.setPosition(position);
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLUE);
        renderer.rect(hitbox.x, hitbox.y, hitbox.getWidth(), hitbox.getHeight());
    }

    public int getID()
    {
        return id;
    }
}
