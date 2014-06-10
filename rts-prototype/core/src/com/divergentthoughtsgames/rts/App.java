package com.divergentthoughtsgames.rts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.world.SelectedEntities;
import com.divergentthoughtsgames.rts.world.UnitGroups;
import com.divergentthoughtsgames.rts.world.World;


public abstract class App
{
	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 600;
	
	private static double gameTime;
	private static double deltaTime;
	
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
	
	private static boolean debug = false;
	
	/**
	 * Whether debugging is currently enabled. Debugging adds additional logging and visualizations.
	 * @return true if debugging is enabled.
	 */
	public static boolean debugEnabled()
	{
		return debug;
	}
	
	/**
	 * Sets whether debugging is enabled.
	 * @param val true to enable debugging, or false otherwise.
	 */
	public static void setDebug(boolean val)
	{
		debug = val;
	}
}