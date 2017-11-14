package com.rare.snake.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.rare.snake.Main;

public class SplashScreen implements Screen {
    private Main game;
    private Texture splashScreenTexture;
    private long startTime;

    public SplashScreen(Main game) {
        this.game = game;
        splashScreenTexture = new Texture("screens/splashScreen.png");
        startTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(splashScreenTexture, 0, 0);
        game.batch.end();

        if(TimeUtils.timeSinceMillis(startTime) >= 3000L) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        splashScreenTexture.dispose();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
