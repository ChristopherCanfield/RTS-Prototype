package com.divergentthoughtsgames.train;

import com.divergentthoughtsgames.train.graphics.Graphics;
import com.divergentthoughtsgames.train.world.SelectedEntities;


public abstract class App
{
	public static final float SCREEN_WIDTH = 800;
	public static final float SCREEN_HEIGHT = 600;
	
	public static Graphics graphics;
	public static World world;
	
	public static SelectedEntities selected = new SelectedEntities();
	
	public static boolean debug = false;
}