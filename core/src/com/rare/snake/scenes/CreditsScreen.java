package com.rare.snake.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rare.snake.Main;

public class CreditsScreen implements Screen {
    private Main game;
    private Texture credits;
    private Texture backButtonNormal;
    private Texture backButtonOver;

    public CreditsScreen(Main game){
        this.game = game;
        credits = new Texture("screens/creditsScreen.png");
        backButtonNormal = new Texture("buttons/backbut_n.png");
        backButtonOver = new Texture("buttons/backbut_o.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(credits, 0, 0);

        // Back button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + backButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * .15f + backButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * .15f - backButtonNormal.getHeight() / 2) {
            game.batch.draw(backButtonOver, Gdx.graphics.getWidth() / 2 - backButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * .15f - backButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                game.setScreen(new MenuScreen(game));
            }
        }
        else
            game.batch.draw(backButtonNormal, Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * .15f - backButtonNormal.getHeight() / 2);

        game.batch.end();
    }

    @Override
    public void dispose() {
        credits.dispose();
        backButtonNormal.dispose();
        backButtonOver.dispose();
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
