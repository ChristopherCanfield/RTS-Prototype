package com.divergentthoughtsgames.rts.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.NavMap;

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
	
	/**
	 * Finds the node that contains the specified x,y coordinate. This is a shortcut for
	 * <code>NavMap.getNode(App.world.getNavMap(), x, y)</code>.
	 * @param x
	 * @param y
	 * @return the node that contains the specified x,y coordinate.
	 */
	public static Node node(int x, int y)
	{
		return NavMap.getNode(App.world.getNavMap(), x, y);
	}
	
	public static Node node(float x, float y)
	{
		return node((int)x, (int)y);
	}
	
	public static Node node(Entity e)
	{
		return node(e.getX(), e.getY());
	}
}
