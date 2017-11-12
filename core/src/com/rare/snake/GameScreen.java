package com.rare.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
    public CellType[][] grid;
    public Vector2 berry;
    public int colsNrows = 20;
    public int scl = Gdx.graphics.getWidth()/colsNrows;
    public boolean gameOver = false;
    private Snake snake;
    private Renderer renderer; // This thing will draw everything
    private Vector2 nextBerryPos;

    public GameScreen(){
        grid = new CellType[colsNrows][colsNrows];
        snake = new Snake(this);
        renderer = new Renderer(this);
        nextBerryPos = new Vector2();
        restart();
    }

    @Override
    public void render(float delta) {
        renderer.render();
        snake.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            restart();
            snake.restart();
        }
    }

    public void createBerry(){
        do {
            nextBerryPos = new Vector2(MathUtils.random(1, colsNrows-2), MathUtils.random(1, colsNrows-2));
        } while (grid[(int)nextBerryPos.x][(int)nextBerryPos.y] != CellType.EMPTY);

        berry = nextBerryPos;
        grid[(int)berry.x][(int)berry.y] = CellType.BERRY;
    }

    public void gameOver(int tailLenght){
        gameOver = true;
    }

    public void restart(){
        // Filling the grid
        for (int x = 0; x < colsNrows; x++) {
            for (int y = 0; y < colsNrows; y++) {
                if(x == 0 || x == colsNrows-1 || y == 0 || y == colsNrows-1){ // Making all the edges wall
                    grid[x][y] = CellType.WALL;
                } else {
                    grid[x][y] = CellType.EMPTY; // Else it will be empty
                }
            }
        }
        // Making 3 centre cells the snake by default
        grid[colsNrows/2-1][colsNrows/2] = CellType.SNAKE;
        grid[colsNrows/2][colsNrows/2] = CellType.SNAKE;
        grid[colsNrows/2+1][colsNrows/2] = CellType.SNAKE;

        gameOver = false;

        createBerry();
    }

    @Override
    public void dispose() {
        renderer.dispose();
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
