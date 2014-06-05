package com.divergentthoughtsgames.rts;

import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.world.SelectedEntities;


public abstract class App
{
	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 600;
	
	public static Graphics graphics;
	public static World world;
	
	public static SelectedEntities selected = new SelectedEntities();
	
	public static boolean debug = false;
}