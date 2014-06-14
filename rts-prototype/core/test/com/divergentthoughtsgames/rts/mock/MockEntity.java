package com.divergentthoughtsgames.rts.mock;

import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.world.Entity;

public class MockEntity extends Entity
{
	public MockEntity()
	{
		super(null);
		selectable = true;
	}
	
	public MockEntity(int x, int y, int width, int height)
	{
		super(null);
		rect = new Rectangle(x, y, width, height);
	}
	
	@Override
	protected void setControllers()
	{
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
