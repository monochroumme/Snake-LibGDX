package com.rare.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Renderer {
    private GameScreen gameScreen;
    private ShapeRenderer shapeRenderer;

    public Renderer(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        shapeRenderer = new ShapeRenderer();
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Drawing walls
        shapeRenderer.rect(0,0, gameScreen.scl, Gdx.graphics.getHeight()); // Left
        shapeRenderer.rect(Gdx.graphics.getWidth() - gameScreen.scl, 0, gameScreen.scl, Gdx.graphics.getHeight()); // Right
        shapeRenderer.rect(gameScreen.scl, 0, Gdx.graphics.getWidth() - gameScreen.scl, gameScreen.scl);
        shapeRenderer.rect(gameScreen.scl, Gdx.graphics.getHeight() - gameScreen.scl, Gdx.graphics.getWidth() - gameScreen.scl, gameScreen.scl);

        // Drawing the snake in the scene
        for (int x = 1; x < gameScreen.colsNrows - 1; x++) {
            for (int y = 1; y < gameScreen.colsNrows - 1; y++) {
                if(gameScreen.grid[x][y] == CellType.SNAKE)
                    shapeRenderer.rect(x * gameScreen.scl, y * gameScreen.scl, gameScreen.scl, gameScreen.scl);
            }
        }

        // Drawing the berry in the scene
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(gameScreen.berry.x * gameScreen.scl, gameScreen.berry.y * gameScreen.scl, gameScreen.scl, gameScreen.scl);
        shapeRenderer.setColor(Color.WHITE);

        shapeRenderer.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
