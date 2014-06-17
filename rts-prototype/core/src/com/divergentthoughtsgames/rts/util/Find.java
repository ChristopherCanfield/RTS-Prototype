package com.divergentthoughtsgames.rts.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.NavMap;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.Entity;

public abstract class Find
{
	/**
	 * Returns a list of all entities that intersect with the specified rectangle.
	 * @param rect the bounding rectangle.
	 * @param entities the list of entities to check.
	 * @return the list of all entities that intersect with the specified rectangle.
	 */
	public static List<Entity> allIntersections(Rectangle rect, Entity[] entities)
	{
		ArrayList<Entity> found = new ArrayList<>();
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
	 * Returns a list of all nodes that intersect with the specified entity.
	 * @param entity the entity to check the nodes against.
	 * @return list of all nodes that intersect with the specified entity.
	 */
	public static List<Node> allNodes(Entity entity)
	{
		// TODO (6/17/2014): this is far too inefficient.
		ArrayList<Node> found = new ArrayList<>();
		Rectangle entRect = entity.getRect();
		Node[] navGraph = App.world.getNavMap().getNavGraph();
		
		for (Node node : navGraph)
		{
			if (node.overlaps(entRect))
			{
				found.add(node);
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
	
	/**
	 * Finds the node that contains the specified x,y coordinate. This is a shortcut for
	 * <code>NavMap.getNode(App.world.getNavMap(), x, y)</code>.
	 * @param x
	 * @param y
	 * @return the node that contains the specified x,y coordinate.
	 */
	public static Node node(float x, float y)
	{
		return node((int)x, (int)y);
	}
	
	/**
	 * Finds the node that contains the specified entity. This is a shortcut for
	 * <code>NavMap.getNode(App.world.getNavMap(), entity.getX(), entity.getY())</code>.
	 * @param x
	 * @param y
	 * @return the node that contains the specified entity.
	 */
	public static Node node(Entity e)
	{
		return node(e.getCenterX(), e.getCenterY());
	}
	
	/**
	 * Returns a list of entity bounding rectangles that overlap with this entity.
	 * @param entity
	 * @param entities the list of entities to check against.
	 * @return a list of entity bounding rectangles that overlap with this entity.
	 */
	public static List<EntityIntersection> overlappingEntities(Entity entity, Entity[] entities)
	{
		ArrayList<EntityIntersection> intersections = new ArrayList<>();
		for (final Entity e : entities)
		{
			if (!entity.equals(e))
			{
				Rectangle rect = new Rectangle();
				if (Intersector.intersectRectangles(entity.getRect(), e.getRect(), rect))
				{
					intersections.add(new EntityIntersection(e, rect));
				}
			}
		}
		return intersections;
	}
}
