package com.kabirlal.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kabirlal.helpers.AssetLoader;
import com.kabirlal.helpers.InputHandler;
import com.kabirlal.helpers.ScoreManager;
import com.kabirlal.screens.GameScreen;

public class UIManager {
    private Stage gameStage;
    private Stage menuStage;
    private Stage gameOverStage;

    private Label scoreText;
    private Label missedHivesText;
    private Skin skin;
    private TextButton startButton;
    private TextButton restartButton;
    private TextButton backToMenuButton;
    private Label gameoverScore;
    private Label gameoverHighScore;

    private Viewport viewport;
    private GameScreen screen;

    public UIManager(Viewport viewport, GameScreen screen)
    {
        this.screen = screen;
        skin = AssetLoader.skin;
        this.viewport = viewport;

        //Setup the UIs but render them based on the gamestate
        setupMenuUI();
        setupGameUI();
        setupGameOverUI();

    }

    public void setupGameUI()
    {
        gameStage = new Stage(viewport);

        scoreText = new Label("Score : " + ScoreManager.getScore(), skin);
        scoreText.setPosition(10, 1000);

        missedHivesText = new Label("Missed Hives: ", skin);
        missedHivesText.setPosition(400, 1000);


        gameStage.addActor(scoreText);
        gameStage.addActor(missedHivesText);

    }

    public void setupMenuUI()
    {
        menuStage = new Stage(viewport);

        startButton = new TextButton("Start", skin);
        startButton.setSize(412, 96);
        startButton.setPosition(170, Gdx.graphics.getHeight() - 340 - 96);
        startButton.addListener(new InputListener()
        {
           public boolean touchDown(InputEvent event, float X, float y, int pointer, int button)
           {
               return true;
           }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                screen.setCurrentState(GameScreen.GameState.RUNNING);
                Gdx.input.setInputProcessor(new InputHandler(screen.getWorld().getBear()));
            }
        });

        menuStage.addActor(startButton);
    }


    public void setupGameOverUI()
    {
        gameOverStage = new Stage(viewport);

        restartButton = new TextButton("Restart", skin);
        restartButton.setSize(600, 100);
        restartButton.setPosition(viewport.getScreenWidth() / 2 - restartButton.getWidth() / 2, viewport.getScreenHeight() / 2 - restartButton.getHeight());

        restartButton.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float X, float y, int pointer, int button)
            {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                screen.getWorld().reset();
                screen.setCurrentState(GameScreen.GameState.RUNNING);
            }

        });

        backToMenuButton = new TextButton("Return to Menu", skin);
        backToMenuButton.setSize(600, 100);
        backToMenuButton.setPosition(restartButton.getX(), restartButton.getY() + restartButton.getHeight() + 10);

        backToMenuButton.addListener(new InputListener()
        {
            public boolean touchDown(InputEvent event, float X, float y, int pointer, int button)
            {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                screen.setCurrentState(GameScreen.GameState.MENU);
                screen.getWorld().reset();
                Gdx.input.setInputProcessor(menuStage);
            }

        });

        gameoverScore = new Label("Score: " + ScoreManager.getScore(), skin);
        gameoverScore.setPosition(backToMenuButton.getX(), backToMenuButton.getHeight() - 40);

        gameoverHighScore = new Label("High Score: " + ScoreManager.highScore, skin);
        gameoverHighScore.setPosition(Gdx.graphics.getWidth() - gameoverScore.getX(), gameoverScore.getY());

        gameOverStage.addActor(gameoverHighScore);
        gameOverStage.addActor(gameoverScore);
        gameOverStage.addActor(restartButton);
        gameOverStage.addActor(backToMenuButton);
    }

    public void updateAndRenderGameUI(int width, int height)
    {
        if(!Gdx.input.getInputProcessor().getClass().equals(InputHandler.class))
            Gdx.input.setInputProcessor(new InputHandler(screen.getWorld().getBear()));
        scoreText.setText("Score : " + ScoreManager.getScore());
        if(ScoreManager.hivesMissed == 0)
            missedHivesText.setText("Missed Hives: 0");
        else
            missedHivesText.setText("Missed Hives:");
        gameStage.getViewport().update(width, height);
        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
    }




    public void update(GameScreen.GameState currentState)
    {
        if(currentState == GameScreen.GameState.RUNNING)
            this.updateAndRenderGameUI(1920, 1080);
        else if(currentState == GameScreen.GameState.MENU)
            this.updateAndRenderMenuUI(1920, 1080);
        else if(currentState == GameScreen.GameState.GAMEOVER)
            this.updateAndRenderGameOverUI(1920, 1080);
    }

    public void updateAndRenderMenuUI(int width, int height)
    {
        if(!Gdx.input.getInputProcessor().getClass().equals(menuStage))
            Gdx.input.setInputProcessor(menuStage);
        menuStage.getViewport().update(width, height);
        menuStage.act(Gdx.graphics.getDeltaTime());
        menuStage.draw();
    }

    public void updateAndRenderGameOverUI(int width, int height)
    {
        if(!Gdx.input.getInputProcessor().getClass().equals(gameOverStage))
            Gdx.input.setInputProcessor(gameOverStage);
        gameoverScore.setText("Score: " + ScoreManager.getScore());
        gameoverHighScore.setText("HighScore: " + ScoreManager.getHighScore());
        gameOverStage.getViewport().update(width, height);
        gameOverStage.act(Gdx.graphics.getDeltaTime());
        gameOverStage.draw();
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
