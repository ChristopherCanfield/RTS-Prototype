package com.divergentthoughtsgames.train;

import java.util.HashMap;
import java.util.UUID;

import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.train.world.Entity;

public class World
{
	private Array<Entity> entities = new Array<>();
	private HashMap<UUID, Entity> entityMap = new HashMap<>();
	
	public void add(Entity e)
	{
		entities.add(e);
		entityMap.put(e.getId(), e);
	}
	
	public Array<Entity> getEntities()
	{
		return entities;
	}
	
	public void update()
	{
		for (final Entity e : entities)
		{
			e.update(this);
		}
	}
}
