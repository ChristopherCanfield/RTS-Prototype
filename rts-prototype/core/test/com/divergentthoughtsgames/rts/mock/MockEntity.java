package com.divergentthoughtsgames.rts.mock;

import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

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
