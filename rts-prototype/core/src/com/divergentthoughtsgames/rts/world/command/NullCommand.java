/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

import com.divergentthoughtsgames.rts.world.Entity;

/**
 * An entity command that does nothing.
 * @author Christopher D. Canfield
 */
public class NullCommand extends AbstractEntityCommand<Entity>
{
	private static NullCommand instance;
	
	public static NullCommand get()
	{
		if (instance == null)
		{
			instance = new NullCommand();
		}
		
		return instance;
	}
	
	private NullCommand()
	{
		super(null);
		setFinished(true);
	}

	@Override
	public void update()
	{
	}
}
