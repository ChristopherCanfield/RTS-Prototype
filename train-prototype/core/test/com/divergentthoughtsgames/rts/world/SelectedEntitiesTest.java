package com.divergentthoughtsgames.rts.world;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.divergentthoughtsgames.rts.World;
import com.divergentthoughtsgames.rts.mock.MockEntity;
import com.divergentthoughtsgames.rts.world.entity.Ogre;

public class SelectedEntitiesTest
{
	private SelectedEntities selected = new SelectedEntities();

	@Test
	public void addContains()
	{
		Entity ent = new MockEntity();
		selected.add(ent);
		assertTrue(selected.contains(ent));
	}

	@Test
	public void testAddAll()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testClear()
	{
		Entity ent = new MockEntity();
		selected.add(ent);
		selected.clear();
		assertTrue(selected.isEmpty());
	}

	@Test
	public void testRemove()
	{
		Entity ent = new MockEntity();
		selected.add(ent);
		assertTrue(!selected.isEmpty());
		selected.remove(ent);
		assertTrue(selected.isEmpty());
	}

	@Test
	public void testGet()
	{
		fail("Not yet implemented");
	}
}
