package com.kabirlal.helpers;


import com.kabirlal.gameobjects.Bear;
import com.kabirlal.gameobjects.Hive;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    public static int score = 0;
    public static List<Integer> scoredHives = new ArrayList<Integer>();
    public static List<Integer> missedHives = new ArrayList<Integer>();
    public static int hivesMissed = 0;

    public static int currentIDChecked = -1;

    public static void updateScore(Bear bear, Hive hive) {
        if (CollisionHandler.areColliding(bear.hitbox, hive.hitbox) && bear.isGrabbing() && hive.getID() != currentIDChecked) {
            score += 5;
            scoredHives.add(hive.getID());
            currentIDChecked = hive.getID();
        }
        // If hive.x pos is less than bear.x pos, increment missed hive
        if (hive.hitbox.x < bear.hitbox.x && !scoredHives.contains(hive.getID()) && !missedHives.contains(hive.getID()))
        {
            missedHives.add(hive.getID());
            hivesMissed = missedHives.size();
        }
    }

    public static int getScore()
    {
        return score;
    }

    public static int getMissedHives()
    {
        return hivesMissed;
    }
}
