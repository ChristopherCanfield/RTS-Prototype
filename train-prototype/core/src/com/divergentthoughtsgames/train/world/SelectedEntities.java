package com.divergentthoughtsgames.train.world;

import java.util.Collection;
import java.util.HashSet;

public class SelectedEntities
{
	// TODO (6/3/2014): make this a singleton.
	
//	private ArrayList<Entity> selected = new ArrayList<Entity>();
	private HashSet<Entity> selected = new HashSet<>();
	
	public SelectedEntities add(Entity e)
	{
		// Confirm that the selected list does not already contain the entity, and the entity
		// is selectable.
		if (selected.contains(e) || !e.selectable)
		{
			return this;
		}
		
		selected.add(e);
		return this;
	}
	
	public boolean contains(Entity e)
	{
		return selected.contains(e);
	}
	
	public void addAll(Collection<Entity> selectedEntities)
	{
		selected.addAll(selectedEntities);
	}
	
	public void clear()
	{
		selected.clear();
	}
	
	public void remove(Entity e)
	{
		selected.remove(e);
	}
	
	public Entity[] get()
	{
		return selected.toArray(new Entity[selected.size()]);
	}
}
