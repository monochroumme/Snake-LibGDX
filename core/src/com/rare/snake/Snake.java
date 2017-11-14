package com.rare.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.rare.snake.scenes.GameScreen;

public class Snake {
    private GameScreen gameScreen;
    private Vector2 head;
    private Array<Vector2> tail;
    private Vector2 dir;
    private long startTimeBetweenMoves;
    private long timeBetweenMoves = 100; // in millis

    public int length = 3;

    public Snake(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        tail = new Array<Vector2>();
        restart();
    }

    public void update(){
        handleInput();

        if(!gameScreen.gameOver && TimeUtils.timeSinceMillis(startTimeBetweenMoves) >= timeBetweenMoves){ // calling move method every certain secs
            startTimeBetweenMoves = System.currentTimeMillis();
            move();
        }
    }

    private void handleInput(){
        //System.out.println(dir + " " + tail.get(tail.size-1) + " " + head + " " + (tail.get(tail.size-1).y + dir.y != head.y));
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && dir.y == 0 && tail.get(tail.size - 1).x != head.x){
                dir.set(0, 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && dir.y == 0 && tail.get(tail.size - 1).x != head.x){
                dir.set(0, -1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && dir.x == 0 && tail.get(tail.size - 1).y != head.y){
                dir.set(-1, 0);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && dir.x == 0 && tail.get(tail.size - 1).y != head.y){
                dir.set(1, 0);
        }
    }

    private void move(){
        // HEAD
        head.add(dir); // Make a step in dir direction

        // Checking collisions with the wall or the snake itself
        if(gameScreen.grid[(int)head.x][(int)head.y] == CellType.WALL || gameScreen.grid[(int)head.x][(int)head.y] == CellType.SNAKE){
            gameScreen.gameOver = true;
            return;
        }

        // Checking collisions with the berry
        if(gameScreen.grid[(int)head.x][(int)head.y] == CellType.BERRY){ // If the snake stepped on a berry then it eats it and not moving its tail
            gameScreen.createBerry();
            length++;
            tail.add(new Vector2(head.x - dir.x, head.y - dir.y));
            gameScreen.grid[(int)head.x][(int)head.y] = CellType.SNAKE; // Make current head's cell type SNAKE as we go farther
            return;
        }

        gameScreen.grid[(int)head.x][(int)head.y] = CellType.SNAKE; // Make current head cell type SNAKE as we go farther

        // TAIL
        gameScreen.grid[(int)tail.get(0).x][(int)tail.get(0).y] = CellType.EMPTY; // Making current tail cell type EMPTY as we are going away from it
        tail.removeIndex(0);
        tail.add(new Vector2(head.x - dir.x, head.y - dir.y));
    }

    public void restart(){
        head = new Vector2(gameScreen.colsNrows/2 + 1, gameScreen.colsNrows/2);
        tail.clear();
        tail.add(new Vector2(gameScreen.colsNrows/2 - 1, gameScreen.colsNrows/2));
        tail.add(new Vector2(gameScreen.colsNrows/2, gameScreen.colsNrows/2));
        dir = new Vector2(1, 0); // moves to the right by default
        length = 3;
        startTimeBetweenMoves = System.currentTimeMillis();
    }
}
