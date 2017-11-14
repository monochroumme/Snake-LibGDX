package com.rare.snake.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.rare.snake.CellType;
import com.rare.snake.Main;
import com.rare.snake.Renderer;
import com.rare.snake.Snake;

public class GameScreen implements Screen {
    public CellType[][] grid;
    public Vector2 berry;
    public int colsNrows = 25;
    public int scl = Gdx.graphics.getWidth()/colsNrows;
    private Snake snake;
    private Renderer renderer; // This thing will draw everything
    private Vector2 nextBerryPos;
    private Main game;

    private FreeTypeFontGenerator fontGenerator80;
    private FreeTypeFontGenerator fontGenerator30;
    private FreeTypeFontGenerator fontGenerator15;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font80;
    private BitmapFont font30;
    private BitmapFont font15;
    private float gameOverWidth;
    private float pressRTextWidth;
    private float pressETextWidth;
    private float snakeLengthTextWidth;
    public boolean gameOver = false;

    public GameScreen(Main game){
        grid = new CellType[colsNrows][colsNrows];
        snake = new Snake(this);
        renderer = new Renderer(this);
        nextBerryPos = new Vector2();
        this.game = game;
        restart();

        GlyphLayout layout = new GlyphLayout();
        fontGenerator80 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/meek.ttf"));
        fontGenerator30 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mm10.ttf"));
        fontGenerator15 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mm10.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 80;
        font80 = fontGenerator80.generateFont(fontParameter);
        font80.setColor(Color.RED);
        layout.setText(font80, "GAME OVER");
        gameOverWidth = layout.width;
        fontParameter.size = 30;
        font30 = fontGenerator30.generateFont(fontParameter);
        font30.setColor(Color.GOLD);
        layout.setText(font30, "PRESS R TO RESTART");
        pressRTextWidth = layout.width;
        layout.setText(font30, "PRESS E TO GO TO MENU");
        pressETextWidth = layout.width;
        fontParameter.size = 15;
        font15 = fontGenerator15.generateFont(fontParameter);
        font15.setColor(Color.RED);
        layout.setText(font15, "SNAKE'S LENGTH: 10");
        snakeLengthTextWidth = layout.width;
    }

    @Override
    public void render(float delta) {
        renderer.render();
        snake.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            restart();
            snake.restart();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            game.setScreen(new MenuScreen(game));
        }

        if(gameOver){
            game.batch.begin();
            font80.draw(game.batch, "GAME OVER", Gdx.graphics.getWidth()/2f - gameOverWidth/2f, Gdx.graphics.getHeight() * 0.7f);
            font30.draw(game.batch, "PRESS R TO RESTART", Gdx.graphics.getWidth()/2f - pressRTextWidth/2f, Gdx.graphics.getHeight() * 0.4f);
            font30.draw(game.batch, "PRESS E TO GO TO MENU", Gdx.graphics.getWidth()/2f - pressETextWidth/2f, Gdx.graphics.getHeight() * 0.3f);
            font15.draw(game.batch, "SNAKE'S LENGTH: " + snake.length, Gdx.graphics.getWidth()/2f - snakeLengthTextWidth/2f, Gdx.graphics.getHeight() * .6f);
            game.batch.end();
        }
    }

    public void createBerry(){
        do {
            nextBerryPos = new Vector2(MathUtils.random(1, colsNrows-2), MathUtils.random(1, colsNrows-2));
        } while (grid[(int)nextBerryPos.x][(int)nextBerryPos.y] != CellType.EMPTY);

        berry = nextBerryPos;
        grid[(int)berry.x][(int)berry.y] = CellType.BERRY;
    }

    private void restart(){
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
        font15.dispose();
        font30.dispose();
        font80.dispose();
        fontGenerator30.dispose();
        fontGenerator80.dispose();
        fontGenerator15.dispose();
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
