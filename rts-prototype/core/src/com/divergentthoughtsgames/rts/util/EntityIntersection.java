/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.util;

import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.world.Entity;

public class EntityIntersection
{
	public final Entity entity;
	public final Rectangle rect;
	
	EntityIntersection(Entity e, Rectangle r)
	{
		entity = e;
		rect = r;
	}
}
