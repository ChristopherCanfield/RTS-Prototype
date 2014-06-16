package com.divergentthoughtsgames.rts.input;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.util.Coords;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Entity;

public class SelectionInputProcessor extends InputAdapter
{
	private final Graphics graphics;
	private final Rectangle rect = new Rectangle();
	
	private boolean isSelecting;
	
	public SelectionInputProcessor(Graphics graphics)
	{
		this.graphics = graphics;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.ESCAPE)
		{
			App.selected.clear();
		}
		
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		Gdx.app.debug("SELECTION", "touchDown " + x + "," + y);
		
		if (button == Buttons.LEFT)
		{
			if (App.selected.isEmpty() || 
					Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))
			{
				Vector3 adjusted = Coords.screenToWorld(x, y);
				graphics.setSelectionRectStart(adjusted.x, adjusted.y);
				rect.x = adjusted.x;
				rect.y = adjusted.y;
				isSelecting = true;
			}
			else
			{
				isSelecting = false;
				// Send move command(s).
			}
		}
		else if (button == Buttons.RIGHT)
		{
			isSelecting = false;
			App.selected.clear();
			resetRect();
			graphics.resetSelectionRect();
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		if (button == Buttons.LEFT && isSelecting)
		{
			Gdx.app.debug("SELECTION", "touchUp " + x + "," + y);
			graphics.resetSelectionRect();
			
			Vector3 adjusted = Coords.screenToWorld(x, y);
			setRectWidthHeight(adjusted.x, adjusted.y);
			List<Entity> newSelected = Find.allIntersections(rect, App.world.getEntities());
			resetRect();
			
			if (!newSelected.isEmpty())
			{
				App.selected.addAll(newSelected);
				
				// Debug.
				if (App.debugEnabled())
				{
					for (Entity e : App.selected.get())
					{
						Gdx.app.debug("Selection Test", e.toString());
					}
				}
				
				isSelecting = false;
				return true;
			}
		}
		
		isSelecting = false;
		return false;
	}
	
	private void setRectWidthHeight(float endX, float endY)
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
	
	private void resetRect()
	{
		rect.x = rect.y = rect.width = rect.height = 0;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		if (isSelecting)
		{
			Vector3 adjusted = Coords.screenToWorld(x, y);
			graphics.setSelectionRectEnd(adjusted.x, adjusted.y);
		}
		return false;
	}
}
