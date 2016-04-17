package com.kabirlal.barelyfun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.screens.SplashScreen;

public class BearlyFun extends Game {

	@Override
	public void create () {
		Gdx.app.log("BearlyFun", "Created!");
		AssetLoader.load();
		this.setScreen(new SplashScreen(this));
	}
}
