package com.kabirlal.helpers;

import com.badlogic.gdx.utils.TimeUtils;
import com.kabirlal.gameworld.GameWorld;

public class LevelManager
{
    private static long startTime;
    private static long currentTime;

    GameWorld world;

    public LevelManager()
    {
        startTime = TimeUtils.millis();
    }

    public void update(GameWorld world)
    {
        this.world = world;
        currentTime = TimeUtils.timeSinceMillis(startTime);
        int speed = (int)(currentTime/4000) + 20;
        world.setSpeed(speed);
    }

}
