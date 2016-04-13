package com.kabirlal.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader
{

    public static boolean loaded = false;

    public static Texture texture;
    public static TextureRegion bg, bg2, grass;

    public static Animation bearAnimation;
    public static TextureRegion villagers, honey, tree[];

    public static Skin skin;
    public static BitmapFont font;

    public static void load()
    {
        loaded = true;
        font = new BitmapFont(Gdx.files.internal("fonts/mainfont.fnt"));
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        texture = new Texture(Gdx.files.internal("gfx/background.png"));

        bg = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
        bg.flip(false, true);
        bg2 = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
        bg2.flip(false, true);
    }

    public void dispose()
    {
        skin.dispose();
        texture.dispose();
        loaded = false;
    }
}
