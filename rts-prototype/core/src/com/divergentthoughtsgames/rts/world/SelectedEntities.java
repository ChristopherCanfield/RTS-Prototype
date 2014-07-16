package com.divergentthoughtsgames.rts.world;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.world.controller.KeyboardEntityMoveController;

public class SelectedEntities
{
	// TODO (6/3/2014): make this a singleton.

//	private ArrayList<Entity> selected = new ArrayList<Entity>();
	private HashSet<Entity> selected = new HashSet<>();

	public SelectedEntities add(Entity e)
	{
		// Confirm that the selected list does not already contain the entity,
		// and the entity is selectable.
		if (!e.selectable)
		{
			return this;
		}
		if (selected.contains(e))
		{
			selected.remove(e);
			return this;
		}

		selected.add(e);

		if (App.debug.possessUnitOnClick())
		{
			e.addController(new KeyboardEntityMoveController());
			App.possessedEntity = e;
			Gdx.app.debug("POSSESS UNIT", "Unit possessed: " + e.toString());
		}

		return this;
	}

	public boolean contains(Entity e)
	{
		return selected.contains(e);
	}

	public void addAll(Collection<Entity> selectedEntities)
	{
		for (final Entity e : selectedEntities)
		{
			add(e);
		}
	}

	public void clear()
	{
		selected.clear();
	}

	public void remove(Entity e)
	{
		selected.remove(e);
	}

	public Entity[] toArray()
	{
		return selected.toArray(new Entity[selected.size()]);
	}

	public Collection<Entity> get()
	{
		return selected;
	}

	public boolean isEmpty()
	{
		return selected.isEmpty();
	}

	public void forEach(Consumer<? super Entity> function)
	{
		selected.forEach(function);
	}
}
