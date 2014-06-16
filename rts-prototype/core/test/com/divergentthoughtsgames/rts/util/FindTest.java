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
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.mock.MockEntity;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.NavMap;
import com.divergentthoughtsgames.rts.world.World;

public class FindTest
{

	@Test
	public void testAllIntersections()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void allNodes()
	{
		Node[] nodes = new Node[4];
		nodes[0] = new Node(0, 0);
		nodes[1] = new Node(10, 10);
		nodes[2] = new Node(100, 200);
		nodes[3] = new Node(15, 10);
		
		NavMap navMap = new NavMap(nodes, 100, 100);
		World world = new World();
		world.setNavMap(navMap);
		App.setWorld(world);
		
		Entity e = new MockEntity(10, 10, 20, 10);
		List<Node> overlapping = Find.allNodes(e);
		
		assertEquals(3, overlapping.size());
		assertSame(nodes[0], overlapping.get(0));
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
		Array<Entity> entities = new Array<Entity>(Entity.class);
		entities.add(new MockEntity(10, 10, 5, 5));
		entities.add(new MockEntity(20, 10, 5, 5));
		entities.add(new MockEntity(12, 5, 10, 10));
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<EntityIntersection> rects = Find.overlappingEntities(ent, entities.toArray());
		assertEquals(2, rects.size());
	}
	
	@Test
	public void testOverlappingEntitiesNoMatches()
	{
		Array<Entity> entities = new Array<Entity>(Entity.class);
		entities.add(new MockEntity(100, 10, 5, 5));
		entities.add(new MockEntity(20, 100, 5, 5));
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<EntityIntersection> rects = Find.overlappingEntities(ent, entities.toArray());
		assertEquals(0, rects.size());
	}
	
	@Test
	public void testOverlappingEntitiesEmptyList()
	{
		Array<Entity> entities = new Array<Entity>(Entity.class);
		
		Entity ent = new MockEntity(11, 10, 5, 10);
		List<EntityIntersection> rects = Find.overlappingEntities(ent, entities.toArray());
		assertEquals(0, rects.size());
	}
}
