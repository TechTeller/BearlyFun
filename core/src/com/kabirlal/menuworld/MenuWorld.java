package com.kabirlal.menuworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kabirlal.helpers.AssetLoader;

public class MenuWorld
{
    Vector2 bgPos;

    public MenuWorld()
    {
        this.bgPos = new Vector2(0, 0);
    }

    public void update(float delta)
    {

    }

    public void render(SpriteBatch batch)
    {
        batch.draw(AssetLoader.menuScreenRegion, bgPos.x, bgPos.y);
    }
}
