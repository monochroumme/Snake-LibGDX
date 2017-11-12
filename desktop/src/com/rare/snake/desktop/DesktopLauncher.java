package com.rare.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rare.snake.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 800;
		config.resizable = false;
		config.vSyncEnabled = false;
		config.title = "Snake by rAre";
		new LwjglApplication(new Main(), config);
	}
}
