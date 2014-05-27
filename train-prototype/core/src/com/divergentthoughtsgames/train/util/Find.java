package com.divergentthoughtsgames.train.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.train.world.Entity;

public abstract class Find
{
	public static List<Entity> allIntersections(Rectangle rect, Array<Entity> entities)
	{
		ArrayList<Entity> found = new ArrayList<Entity>();
		for (Entity e : entities)
		{
			found.add(e);
		}
		
		return found;
	}
}
