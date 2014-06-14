/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

import com.divergentthoughtsgames.rts.world.Entity;

public abstract class AbstractEntityCommand<T extends Entity> implements EntityCommand
{
	protected final T entity;
	private boolean finished;
	
	protected AbstractEntityCommand(T entity)
	{
		this.entity = entity;
	}
	
	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	protected final void setFinished(boolean finished)
	{
		this.finished = finished;
		if (finished)
		{
			onFinished();
		}
	}
	
	/**
	 * Called when true is passed to setFinished. Override this to hook into setFinished.
	 */
	protected void onFinished()
	{
	}
}
