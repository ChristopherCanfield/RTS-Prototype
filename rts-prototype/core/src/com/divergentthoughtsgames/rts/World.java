package com.divergentthoughtsgames.rts;

import java.util.HashMap;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.world.Entity;

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
			e.update();
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		for (final Entity e : entities)
		{
			e.draw(batch);
		}
	}
}
