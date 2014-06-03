package com.divergentthoughtsgames.train.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.train.App;
import com.divergentthoughtsgames.train.graphics.Graphics;
import com.divergentthoughtsgames.train.util.Find;
import com.divergentthoughtsgames.train.world.Entity;

public class SelectionInputProcessor extends InputAdapter
{
	private final Graphics graphics;
	private final Rectangle rect = new Rectangle();
	
	public SelectionInputProcessor(Graphics graphics)
	{
		this.graphics = graphics;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		System.out.println("touchDown " + x + "," + y);
		graphics.setSelectionRectStart(x, Gdx.graphics.getHeight() - y);
		rect.x = x;
		rect.y = Gdx.graphics.getHeight() - y;
		App.selected.clear();
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		System.out.println("touchUp " + x + "," + y);
		graphics.resetSelectionRect();
		setRectWidthHeight(x, Gdx.graphics.getHeight() - y);
		App.selected.addAll(Find.allIntersections(rect, App.world.getEntities()));
		
		// Debug.
		for (Entity e : App.selected.get())
		{
			Gdx.app.debug("Selection Test", e.toString());
		}
		
		return false;
	}
	
	private void setRectWidthHeight(int endX, int endY)
	{
		if (endX - rect.x < 0)
		{
			rect.width = rect.x - endX;
			rect.x = endX;
		}
		else
		{
			rect.width = endX - rect.x;
		}
		
		if (endY - rect.y < 0)
		{
			rect.height = rect.y - endY;
			rect.y = endY;
		}
		else
		{
			rect.height = endY - rect.y;
		}
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		graphics.setSelectionRectEnd(x, Gdx.graphics.getHeight() - y);
		
		return false;
	}
}
