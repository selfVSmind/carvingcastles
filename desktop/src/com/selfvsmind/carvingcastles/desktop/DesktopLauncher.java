package com.selfvsmind.carvingcastles.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.selfvsmind.carvingcastles.CCLevelEditor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Carving Castles";
		config.addIcon("images/icon-256.png", Files.FileType.Internal);
		config.addIcon("images/icon-64.png", Files.FileType.Internal);
		config.addIcon("images/icon-32.png", Files.FileType.Internal);

		config.width = 1680;
		config.height = 850;
//		config.width = 1920;
//		config.height = 1080;
//		config.fullscreen = true;
		new LwjglApplication(new CCLevelEditor(), config);
	}
}
