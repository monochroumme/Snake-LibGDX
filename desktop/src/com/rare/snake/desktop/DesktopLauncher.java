package com.rare.snake.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rare.snake.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 700;
		config.height = 700;
		config.resizable = false;
		config.vSyncEnabled = false;
		config.title = "Snake by rAre";
		config.addIcon("icons/16.png", Files.FileType.Internal);
		config.addIcon("icons/32.png", Files.FileType.Internal);
		config.addIcon("icons/128.png", Files.FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
