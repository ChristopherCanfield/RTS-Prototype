package com.divergentthoughtsgames.train.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SelectedEntities
{
	// TODO (6/3/2014): make this a singleton.
	
	private ArrayList<Entity> selected = new ArrayList<Entity>();
	
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
	
	public List<Entity> get()
	{
		return selected;
	}
}
