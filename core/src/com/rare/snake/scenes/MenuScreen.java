package com.rare.snake.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rare.snake.Main;

public class MenuScreen implements Screen {
    private Main game;

    private Texture snakeLogo;
    private Texture playButtonNormal;
    private Texture playButtonOver;
    private Texture creditsButtonNormal;
    private Texture creditsButtonOver;
    private Texture exitButtonNormal;
    private Texture exitButtonOver;

    private final float SNAKELOGO_Y_PERCENT = .67f;
    private final float PLAYBUTTON_Y_PERCENT = .45f;
    private final float CREDITS_Y_PERCENT = .235f;
    private final float EXIT_Y_PERCENT = .095f;

    public MenuScreen(Main game){
        this.game = game;

        snakeLogo = new Texture("snakelogo.png");
        playButtonNormal = new Texture("buttons/playbut_n.png");
        playButtonOver = new Texture("buttons/playbut_o.png");
        creditsButtonNormal = new Texture("buttons/creditsbut_n.png");
        creditsButtonOver = new Texture("buttons/creditsbut_o.png");
        exitButtonNormal = new Texture("buttons/exitbut_n.png");
        exitButtonOver = new Texture("buttons/exitbut_o.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(snakeLogo, Gdx.graphics.getWidth() / 2 - snakeLogo.getWidth() / 2, Gdx.graphics.getHeight() * SNAKELOGO_Y_PERCENT);
        
        // Play button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + playButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - playButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * PLAYBUTTON_Y_PERCENT + playButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * PLAYBUTTON_Y_PERCENT - playButtonNormal.getHeight() / 2) {
            game.batch.draw(playButtonOver, Gdx.graphics.getWidth() / 2 - playButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * PLAYBUTTON_Y_PERCENT - playButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                game.setScreen(new GameScreen(game));
            }
        }
        else
            game.batch.draw(playButtonNormal, Gdx.graphics.getWidth() / 2 - playButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * PLAYBUTTON_Y_PERCENT - playButtonNormal.getHeight() / 2);

        // Credits button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + creditsButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - creditsButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * CREDITS_Y_PERCENT + creditsButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * CREDITS_Y_PERCENT - creditsButtonNormal.getHeight() / 2){
            game.batch.draw(creditsButtonOver, Gdx.graphics.getWidth()/2 - creditsButtonOver.getWidth()/2, Gdx.graphics.getHeight()*CREDITS_Y_PERCENT - creditsButtonOver.getHeight()/2);
            if(Gdx.input.justTouched()){
                game.setScreen(new CreditsScreen(game));
            }
        } else
            game.batch.draw(creditsButtonNormal, Gdx.graphics.getWidth()/2 - creditsButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*CREDITS_Y_PERCENT - creditsButtonNormal.getHeight()/2);

        // Exit button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + exitButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - exitButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * EXIT_Y_PERCENT + exitButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * EXIT_Y_PERCENT - exitButtonNormal.getHeight() / 2) {
            game.batch.draw(exitButtonOver, Gdx.graphics.getWidth() / 2 - exitButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * EXIT_Y_PERCENT - exitButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                Gdx.app.exit();
            }
        }
        else
            game.batch.draw(exitButtonNormal, Gdx.graphics.getWidth()/2 - exitButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*EXIT_Y_PERCENT - exitButtonNormal.getHeight()/2);

        game.batch.end();
    }

    @Override
    public void dispose() {
        snakeLogo.dispose();
        playButtonNormal.dispose();
        playButtonOver.dispose();
        creditsButtonNormal.dispose();
        creditsButtonOver.dispose();
        exitButtonNormal.dispose();
        exitButtonOver.dispose();
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
