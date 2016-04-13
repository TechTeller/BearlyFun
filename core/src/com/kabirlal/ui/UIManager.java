package com.kabirlal.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.ScoreManager;

public class UIManager {
    private Stage gameStage;
    private Label scoreText;
    private Label missedHivesText;
    private Skin skin;

    private Viewport viewport;

    public UIManager(Viewport viewport)
    {
        skin = AssetLoader.skin;
        this.viewport = viewport;
        gameStage = new Stage(viewport);

        scoreText = new Label("Score : " + ScoreManager.getScore(), skin);
        scoreText.setPosition(10, 1000);

        missedHivesText = new Label("Missed Hives: " + ScoreManager.hivesMissed, skin);
        missedHivesText.setPosition(400, 1000);


        gameStage.addActor(scoreText);
        gameStage.addActor(missedHivesText);
    }

    public void update(int width, int height)
    {
        scoreText.setText("Score : " + ScoreManager.getScore());
        missedHivesText.setText("Missed Hives : " + ScoreManager.hivesMissed);
        gameStage.getViewport().update(width, height);
    }

    public void render()
    {
        this.update(1920, 1080);
        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
    }

    public void resize(int width, int height)
    {
        gameStage.getViewport().update(width, height, true);
    }

    public void dispose()
    {
        gameStage.dispose();
    }
}
