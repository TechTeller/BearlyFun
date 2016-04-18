package com.kabirlal.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.kabirlal.screens.GameScreen;

public class InputHandler implements InputProcessor
{
    GameScreen screen;

    public InputHandler(GameScreen screen)
    {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(screen.getCurrentState() == GameScreen.GameState.RUNNING)
        {
            if(screenX <= Gdx.graphics.getWidth() / 2)
            {
                screen.getWorld().getBear().jump();
            }
            else
            {
                screen.getWorld().getBear().doGrab();
                AssetLoader.grabGood.play();
            }
        }
        else if(screen.getCurrentState() == GameScreen.GameState.MENU)
        {
            screen.setCurrentState(GameScreen.GameState.RUNNING);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screen.getWorld().getBear().isGrabbing())
            screen.getWorld().getBear().undoGrab();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
