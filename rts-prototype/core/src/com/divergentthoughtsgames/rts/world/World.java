package com.divergentthoughtsgames.rts.world;

import java.util.HashMap;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.App;

public class World
{
	private Array<Entity> entities = new Array<>();
	private HashMap<UUID, Entity> entityMap = new HashMap<>();
	
	private Array<Entity> entitiesToRemove = new Array<Entity>();
	
	private NavMap navMap;
	
	public void add(Entity e)
	{
		entities.add(e);
		entityMap.put(e.getId(), e);
	}
	
	public Array<Entity> getEntities()
	{
		return entities;
	}
	
	public void setNavMap(NavMap navMap)
	{
		this.navMap = navMap;
	}
	
	public NavMap getNavMap()
	{
		return navMap;
	}
	
	public int getWidth()
	{
		// Temporary check during testing.
		if (navMap != null)
		{
			return navMap.width;
		}
		else
		{
			return 2000;
		}
	}
	
	public int getHeight()
	{
		// Temporary check during testing.
		if (navMap != null)
		{
			return navMap.height;
		}
		else
		{
			return 2000;
		}
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
	
	public void drawNavGraph(ShapeRenderer renderer)
	{
		navMap.draw(renderer);
	}
	
	public void draw(SpriteBatch batch)
	{
		for (final Entity e : entities)
		{
			e.draw(batch);
		}
	}
}
