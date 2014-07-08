/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.effect.ClickLocatorEffect;

/**
 * Creates an effect in the location of a click.
 * @author Christopher D. Canfield
 */
public class ClickLocator extends InputAdapter
{
	private boolean isDragging;
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO (7/8/2014): display cancel click (red) when 2 fingers are clicked.
		Color color = null;
		if (App.selected.isEmpty())
		{
			color = Color.NAVY;
		}
		else
		{
			color = (button == Buttons.LEFT) ? Color.GREEN : Color.RED;
		}
		
		ClickLocatorEffect effect = new ClickLocatorEffect(screenX, screenY, color);
		App.graphics.addEffect(effect);
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		isDragging = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (isDragging)
		{
			ClickLocatorEffect effect = new ClickLocatorEffect(screenX, screenY);
			App.graphics.addEffect(effect);
			isDragging = false;
		}
		return false;
	}
}
