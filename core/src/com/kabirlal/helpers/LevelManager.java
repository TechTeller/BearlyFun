package com.kabirlal.helpers;

import com.badlogic.gdx.utils.TimeUtils;
import com.kabirlal.gameworld.GameWorld;

public class LevelManager
{
    private static long startTime = TimeUtils.millis();
    private static long currentTime;

    GameWorld world;

    public void update(GameWorld world)
    {
        this.world = world;
        currentTime = TimeUtils.timeSinceMillis(startTime);
        int speed = (int)(currentTime/2000) + 20;
        world.setSpeed(speed);
        System.out.println(speed);
    }

}
