package com.divergentthoughtsgames.rts.world;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.divergentthoughtsgames.rts.World;
import com.divergentthoughtsgames.rts.mock.MockEntity;
import com.divergentthoughtsgames.rts.world.entity.Ogre;

public class SelectedEntitiesTest
{
	private SelectedEntities selected;
	
	@Before
	public void setup()
	{
		selected = new SelectedEntities();
	}
	
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
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.add(new MockEntity());
		list.add(new MockEntity());
		
		selected.addAll(list);
		
		for (Entity e : list)
		{
			assertTrue(selected.contains(e));
		}
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
		Entity ent = new MockEntity();
		selected.add(ent);
		
		assertTrue(selected.get().contains(ent));
	}
}
