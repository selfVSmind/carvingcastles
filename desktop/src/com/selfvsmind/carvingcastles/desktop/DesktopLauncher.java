/**
 *
 * Created by Jason Lambert.
 * http://jasonlambert.io
 * jason@jasonlambert.io
 *
 * http://www.github.com/selfVSmind/carvingcastles
 *
 */

package com.selfvsmind.carvingcastles.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.selfvsmind.carvingcastles.CCLevelEditor;
import com.selfvsmind.carvingcastles.jython.TestJythonType;
import com.selfvsmind.carvingcastles.jython.JythonFactory;

public class DesktopLauncher {
	public static void main (String[] arg) {
		JythonFactory factory = new JythonFactory("TestJython", "TestJython");

		TestJythonType example  = factory.create();
		example.say_hello();



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
