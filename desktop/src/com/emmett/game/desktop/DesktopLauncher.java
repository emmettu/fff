package com.emmett.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.emmett.game.SecretSanta;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fabulous Flatmate Finder";
		config.height = 720;
		config.width = 1080;
		new LwjglApplication(new SecretSanta(), config);
	}
}
