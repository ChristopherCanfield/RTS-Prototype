/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.controller;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.util.EntityIntersection;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Controller;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Prevents solid entities from moving through each other.
 * @author Christopher D. Canfield
 */
public class SolidEntityController implements Controller
{
	private static SolidEntityController instance;
	
	public static SolidEntityController get()
	{
		if (instance == null)
		{
			instance = new SolidEntityController();
		}
		return instance;
	}
	
	@Override
	public void update(Entity entity, World world)
	{
		List<EntityIntersection> overlapping = Find.overlappingEntities(entity, world.getEntities());
		for (final EntityIntersection intersection : overlapping)
		{
			// TODO: Perform polygon bounding box tests once the intersection between the AABB's have been confirmed?
			if (intersection.entity.isSolid())
			{
				// TODO: this isn't the right way to do this.
				entity.move(-intersection.rect.width / 2.f, -intersection.rect.height / 2.f);
			}
		}
	}
}
