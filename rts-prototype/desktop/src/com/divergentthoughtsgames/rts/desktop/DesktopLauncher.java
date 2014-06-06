package com.divergentthoughtsgames.rts.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.divergentthoughtsgames.rts.RtsPrototype;

public class DesktopLauncher
{
	@SuppressWarnings("unused")
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new RtsPrototype(), config);
	}
}
