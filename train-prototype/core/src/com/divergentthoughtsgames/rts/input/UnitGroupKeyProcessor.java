package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.rts.App;

public class UnitGroupKeyProcessor extends InputAdapter
{
	private boolean controlIsDown = false;
	
	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.F1)
		{
			App.debug = !App.debug;
		}
		else if (keycode == Keys.CONTROL_LEFT || keycode == Keys.CONTROL_RIGHT)
		{
			controlIsDown = false;
		}
		
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.CONTROL_LEFT || keycode == Keys.CONTROL_RIGHT)
		{
			controlIsDown = true;
		}
		
		return false;
	}
}
