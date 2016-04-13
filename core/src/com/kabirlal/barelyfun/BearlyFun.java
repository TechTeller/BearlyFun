package com.kabirlal.barelyfun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kabirlal.screens.GameScreen;

public class BearlyFun extends Game {

	@Override
	public void create () {
		Gdx.app.log("BearlyFun", "Created!");
		this.setScreen(new GameScreen());
	}
}
