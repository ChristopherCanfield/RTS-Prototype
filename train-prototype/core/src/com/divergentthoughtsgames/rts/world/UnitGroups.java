package com.divergentthoughtsgames.rts.world;

import java.util.Collection;
import java.util.HashSet;

public class UnitGroups
{
	// Checked in the methods below.
	@SuppressWarnings("unchecked")
	private HashSet<Entity>[] units = new HashSet[10];
	
	public UnitGroups add(int unitIndex, Entity e)
	{
		// Confirm that the selected list does not already contain the entity, and the entity
		// is selectable.
		if (!e.selectable || units[unitIndex].contains(e))
		{
			return this;
		}
		
		units[unitIndex].add(e);
		return this;
	}
	
	public boolean contains(int unitIndex, Entity e)
	{
		return units[unitIndex].contains(e);
	}
	
	public void addAll(int unitIndex, Collection<Entity> selectedEntities)
	{
		for (final Entity e : selectedEntities)
		{
			add(unitIndex, e);
		}
	}
	
	public void clear(int unitIndex)
	{
		units[unitIndex].clear();
	}
	
	public void remove(int unitIndex, Entity e)
	{
		units[unitIndex].remove(e);
	}
	
	public Entity[] get(int unitIndex)
	{
		return units[unitIndex].toArray(new Entity[units[unitIndex].size()]);
	}
}
