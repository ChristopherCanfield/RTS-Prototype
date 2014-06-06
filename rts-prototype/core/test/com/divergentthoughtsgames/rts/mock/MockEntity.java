package com.divergentthoughtsgames.rts.mock;

import com.divergentthoughtsgames.rts.World;
import com.divergentthoughtsgames.rts.world.Entity;

public class MockEntity extends Entity
{
	public MockEntity()
	{
		super(null);
		selectable = true;
	}

	@Override
	protected void onDraw()
	{
	}

	@Override
	protected void onUpdate()
	{
	}

	@Override
	protected void onDispose()
	{
	}
}
