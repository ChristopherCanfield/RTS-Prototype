package com.divergentthoughtsgames.train.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.train.graphics.Graphics;

public class SelectionInputProcessor extends InputAdapter
{
	private final Graphics graphics;
	
	public SelectionInputProcessor(Graphics graphics)
	{
		this.graphics = graphics;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		System.out.println("touchDown " + x + "," + y);
		graphics.setSelectionRectStart(x, Gdx.graphics.getHeight() - y);
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		System.out.println("touchUp " + x + "," + y);
		graphics.resetSelectionRect();
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		graphics.setSelectionRectEnd(x, Gdx.graphics.getHeight() - y);
		
		return false;
	}
}
