package com.kabirlal.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader
{

    public static boolean loaded = false;

    public static Texture texture;
    public static TextureRegion bg, bg2, grass;

    public static Animation bearAnimation;
    public static TextureRegion villagers, honey, tree[];

    public static void load()
    {
        loaded = true;
        texture = new Texture(Gdx.files.internal("gfx/background.png"));

        bg = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
        bg2 = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }
}
