package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.rts.App;

public class DebugKeyProcessor extends InputAdapter
{
	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.F1)
		{
			App.setDebug(!App.debugEnabled());
		}
		else if (keycode == Keys.F2)
		{
			App.reset();
		}
		
		return false;
	}
}
