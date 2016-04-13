package com.kabirlal.helpers;


import com.kabirlal.gameobjects.Bear;
import com.kabirlal.gameobjects.Hive;

public class ScoreManager {

    public static int score = 0;
    public static int currentIDChecked = -1;

    public static void updateScore(Bear bear, Hive hive)
    {
        if(CollisionHandler.areColliding(bear.hitbox, hive.hitbox) && bear.isGrabbing() && hive.getID() != currentIDChecked)
        {
            score += 5;
            currentIDChecked = hive.getID();
        }
    }
}
