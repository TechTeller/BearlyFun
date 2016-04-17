package com.kabirlal.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader
{

    public static boolean loaded = false;

    public static Texture texture, tree, hive, menuScreen, logo;
    public static TextureRegion bg, bg2, treeRegion, hiveRegion, menuScreenRegion;

    public static Animation bearAnimation;

    public static Skin skin;
    public static BitmapFont font;

    public static Preferences prefs;

    public static void load()
    {
        loaded = true;
        font = new BitmapFont(Gdx.files.internal("fonts/mainfont.fnt"));
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        texture = new Texture(Gdx.files.internal("gfx/background.png"));
        tree = new Texture(Gdx.files.internal("gfx/tree.png"));
        hive = new Texture(Gdx.files.internal("gfx/hive.png"));
        menuScreen = new Texture(Gdx.files.internal("gfx/menuScreen.png"));
        logo = new Texture(Gdx.files.internal("gfx/Logo.png"));

        prefs = Gdx.app.getPreferences("BearlyFun");
        if(!prefs.contains("highScore"))
            prefs.putInteger("highScore", 0);


        bg = new TextureRegion(texture, 1920, 1080);
        bg.flip(false, true);
        bg2 = new TextureRegion(texture, 1920, 1080);
        bg2.flip(false, true);
        menuScreenRegion = new TextureRegion(menuScreen, 1920, 1080);
        menuScreenRegion.flip(false, true);
        treeRegion = new TextureRegion(tree, tree.getWidth(), tree.getHeight());
        treeRegion.flip(false, true);
        hiveRegion = new TextureRegion(hive, hive.getWidth(), hive.getHeight());
        hiveRegion.flip(false, true);
    }

    public void dispose()
    {
        skin.dispose();
        texture.dispose();
        loaded = false;
    }
}
