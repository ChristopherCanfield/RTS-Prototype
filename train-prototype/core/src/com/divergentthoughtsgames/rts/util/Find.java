package com.divergentthoughtsgames.rts.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.WorldMap;

public abstract class Find
{
	/**
	 * Returns a list of all entities that intersect with the specified rectangle.
	 * @param rect the bounding rectangle.
	 * @param entities the list of entities to check.
	 * @return the list of all entities that intersect with the specified rectangle.
	 */
	public static List<Entity> allIntersections(Rectangle rect, Array<Entity> entities)
	{
		ArrayList<Entity> found = new ArrayList<Entity>();
		for (Entity e : entities)
		{
			if (rect.overlaps(e.getRect()))
			{
				found.add(e);
			}
		}
		
		return found;
	}
	
	public static Node node(WorldMap map, int x, int y)
	{
		return WorldMap.getNode(map, x, y);
	}
}
