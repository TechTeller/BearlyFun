package com.kabirlal.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.ScoreManager;

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
        height = width = 61;
        hitbox = new Rectangle(position.x + tree.getWidth() / 2 - width / 2, position.y, width, height);
    }

    public void spawn()
    {
        id++;
        level = MathUtils.random(tree.getPosition().y, 900);
    }

    public void update(float delta)
    {
        if(position.x + tree.getWidth() / 2 < 0)
            spawn();
        position.y = level;
        position.x = tree.getPosition().x + tree.getWidth() / 2;
        hitbox.setPosition(position);
    }

    public void renderHitbox(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLUE);
        renderer.rect(hitbox.x, hitbox.y, hitbox.getWidth(), hitbox.getHeight());
    }

    public void renderGraphic(SpriteBatch batcher)
    {
        if(!ScoreManager.scoredHives.contains(id))
            batcher.draw(AssetLoader.hiveRegion, hitbox.x, hitbox.y);
    }

    public int getID()
    {
        return id;
    }
}
