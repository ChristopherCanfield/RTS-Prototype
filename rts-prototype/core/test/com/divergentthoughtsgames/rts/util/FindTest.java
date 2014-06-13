/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.mock.MockEntity;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

public class FindTest
{

	@Test
	public void testAllIntersections()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testNodeIntInt()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testNodeFloatFloat()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testNodeEntity()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testOverlappingEntities()
	{
		Array<Entity> entities = new Array<Entity>();
		entities.add(new MockEntity(10, 10, 5, 5));
		entities.add(new MockEntity(20, 10, 5, 5));
		entities.add(new MockEntity(12, 5, 10, 10));
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<Rectangle> rects = Find.overlappingEntities(ent, entities);
		assertEquals(2, rects.size());
	}
	
	@Test
	public void testOverlappingEntitiesNoMatches()
	{
		Array<Entity> entities = new Array<Entity>();
		entities.add(new MockEntity(100, 10, 5, 5));
		entities.add(new MockEntity(20, 100, 5, 5));
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<Rectangle> rects = Find.overlappingEntities(ent, entities);
		assertEquals(0, rects.size());
	}
	
	@Test
	public void testOverlappingEntitiesEmptyList()
	{
		Array<Entity> entities = new Array<Entity>();
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<Rectangle> rects = Find.overlappingEntities(ent, entities);
		assertEquals(0, rects.size());
	}
}
