package com.kabirlal.helpers;

import com.badlogic.gdx.utils.TimeUtils;
import com.kabirlal.gameworld.GameWorld;

public class LevelManager
{
    public static long startTime;
    private static long currentTime;

    GameWorld world;

    public LevelManager()
    {
        startTime = TimeUtils.millis();
    }

    public void update(GameWorld world)
    {
        this.world = world;

        //Check for loss condition
        world.setHasEnded(ScoreManager.getMissedHives() >= 3);

        //Speed world gradually
        currentTime = TimeUtils.timeSinceMillis(startTime);
        int speed = (int)(currentTime/4000) + 20;
        world.setSpeed(speed);
    }
}
