package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ShootsAndLadders;

/**v
 * The LibGDX defined code that starts the game on desktop. Defines the game window size and window name,
 * then starts the game. We cannot alter this code much or the game will not run
 * 
 * Authors: @mhunter, @shennessy, @mbehenna, @pmishra
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		ShootsAndLadders.disableWarning();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Shoots and Ladders";
	    config.width = 800;
	    config.height = 480;
	    ShootsAndLadders game = new ShootsAndLadders();
		new LwjglApplication(game, config);
	}
}