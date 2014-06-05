package com.divergentthoughtsgames.rts.world;

import java.util.Collection;
import java.util.HashSet;

public class UnitGroups
{
	// Checked in the methods below.
	@SuppressWarnings("unchecked")
	private HashSet<Entity>[] groups = new HashSet[10];
	
	public UnitGroups()
	{
		for (int i = 0; i < groups.length; ++i)
		{
			groups[i] = new HashSet<Entity>();
		}
	}
	
	public UnitGroups add(int unitIndex, Entity e)
	{
		// Confirm that the selected list does not already contain the entity, and the entity
		// is selectable.
		if (!e.selectable || groups[unitIndex].contains(e))
		{
			return this;
		}
		
		groups[unitIndex].add(e);
		return this;
	}
	
	public boolean contains(int unitIndex, Entity e)
	{
		return groups[unitIndex].contains(e);
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
		groups[unitIndex].clear();
	}
	
	public void remove(int unitIndex, Entity e)
	{
		groups[unitIndex].remove(e);
	}
	
	public Entity[] toArray(int unitIndex)
	{
		return groups[unitIndex].toArray(new Entity[groups[unitIndex].size()]);
	}
	
	public Collection<Entity> get(int unitIndex)
	{
		return groups[unitIndex];
	}
}
