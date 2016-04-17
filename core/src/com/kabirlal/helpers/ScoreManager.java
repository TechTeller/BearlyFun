package com.kabirlal.helpers;


import com.kabirlal.gameworld.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    public static int highScore = getHighScore();
    public static int score = 0;
    public static List<Integer> scoredHives = new ArrayList<Integer>();
    public static List<Integer> missedHives = new ArrayList<Integer>();
    public static int hivesMissed = 0;

    public static int currentIDChecked = -1;

    public static void updateScore(GameWorld world) {

        if (CollisionHandler.areColliding(world.getBear().hitbox, world.getHive().hitbox) && world.getBear().isGrabbing() && world.getHive().getID() != currentIDChecked) {
            score += 5;
            scoredHives.add(world.getHive().getID());
            currentIDChecked = world.getHive().getID();
        }
        // If world.getHive().x pos is less than world.getBear().x pos, increment missed world.getHive()
        if (world.getHive().hitbox.x < world.getBear().hitbox.x && !scoredHives.contains(world.getHive().getID()) && !missedHives.contains(world.getHive().getID()))
        {
            missedHives.add(world.getHive().getID());
            hivesMissed = missedHives.size();
        }
    }

    public static void updateHighScore()
    {
            if(score > highScore)
                setHighScore(score);
    }

    public static int getScore()
    {
        return score;
    }

    public static int getMissedHives()
    {
        return hivesMissed;
    }


    public static void setHighScore(int val)
    {
        AssetLoader.prefs.putInteger("highScore", val);
        AssetLoader.prefs.flush();
    }

    public static int getHighScore()
    {
        return AssetLoader.prefs.getInteger("highScore");
    }
}
