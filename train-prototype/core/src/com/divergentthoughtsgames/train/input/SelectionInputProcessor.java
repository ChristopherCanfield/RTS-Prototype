package com.divergentthoughtsgames.train.input;

import com.badlogic.gdx.InputAdapter;

public class SelectionInputProcessor extends InputAdapter
{
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		System.out.println("touchDown " + x + "," + y);
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		System.out.println("touchUp " + x + "," + y);
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		return false;
	}
}
