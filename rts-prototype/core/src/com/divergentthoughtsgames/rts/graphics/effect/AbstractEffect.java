/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.graphics.effect;

public abstract class AbstractEffect implements Effect
{
	private boolean finished;
	
	@Override
	public final boolean isFinished()
	{
		return finished;
	}

	protected void setFinished(boolean val)
	{
		finished = val;
		if (val)
		{
			onFinished();
		}
	}
	
	protected abstract void onFinished();
}
