package com.divergentthoughtsgames.rts.world;

import java.util.HashMap;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class World
{
	private Array<Entity> entities = new Array<>();
	private HashMap<UUID, Entity> entityMap = new HashMap<>();
	
	private Array<Entity> entitiesToRemove = new Array<Entity>();
	
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
			e.update();
			
			if (e.isDisposed())
			{
				entitiesToRemove.add(e);
			}
		}
		
		entities.removeAll(entitiesToRemove, true);
		for (final Entity e : entitiesToRemove)
		{
			entityMap.remove(e.getId());
		}
		entitiesToRemove.clear();
	}
	
	public void draw(SpriteBatch batch)
	{
		for (final Entity e : entities)
		{
			e.draw(batch);
		}
	}
}
