/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.util.Coords;
import com.divergentthoughtsgames.rts.world.Entity;

public class UnitControlInputProcessor extends InputAdapter
{

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (button == Buttons.LEFT && !App.selected.isEmpty())
		{
			final Vector3 worldCoords = Coords.screenToWorld(screenX, screenY);
			App.selected.forEach((Entity e) -> {
				e.rotateToFace((int)worldCoords.x, (int)worldCoords.y);
				if (App.debugEnabled()) e.logRotation();
			});
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

}
