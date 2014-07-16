package com.divergentthoughtsgames.rts;

import com.divergentthoughtsgames.rts.debug.DebugSettings;
import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.world.SelectedEntities;
import com.divergentthoughtsgames.rts.world.UnitGroups;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Methods and variables that are available from anywhere in the application.
 * @author Christopher D. Canfield
 */
public abstract class App
{
	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 600;

	private static double gameTime;
	private static double deltaTime;

	public static final DebugSettings debug = new DebugSettings();

	/**
	 * The amount of time since the game has begun.
	 * @return the amount of time since the game has begun, in seconds.
	 */
	public static double getGameTime()
	{
		return gameTime;
	}

	/**
	 * Adds the delta to the game time.
	 * @param delta the number of seconds since the last frame.
	 */
	public static void addGameTime(float delta)
	{
		gameTime += delta;
		deltaTime = delta;
	}

	/**
	 * The amount of time that has passed since the last frame, in seconds.
	 * @return
	 */
	public static double getDeltaTime()
	{
		return deltaTime;
	}

	public static Graphics graphics;

	public static void setGraphics(Graphics g)
	{
		graphics = g;
	}

	public static World world;

	public static void setWorld(World w)
	{
		world = w;
	}

	public static SelectedEntities selected = new SelectedEntities();
	public static UnitGroups unitGroups = new UnitGroups();

	public static void reset()
	{
		RtsPrototype.buildWorld();
	}
}