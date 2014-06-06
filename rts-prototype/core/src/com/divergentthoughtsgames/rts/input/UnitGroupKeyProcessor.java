package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.rts.App;

public class UnitGroupKeyProcessor extends InputAdapter
{
	private boolean controlKeyIsDown = false;
	
	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.CONTROL_LEFT || keycode == Keys.CONTROL_RIGHT)
		{
			controlKeyIsDown = false;
		}
		else if (keycode >= Keys.NUM_0 && keycode <= Keys.NUM_9)
		{
			int unitIndex = keycode - Keys.NUM_0;
			if (controlKeyIsDown)
			{
				App.unitGroups.clear(unitIndex);
				App.unitGroups.addAll(unitIndex, App.selected.get());
			}
			else
			{
				App.selected.clear();
				App.selected.addAll(App.unitGroups.get(unitIndex));
			}
		}
		
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.CONTROL_LEFT || keycode == Keys.CONTROL_RIGHT)
		{
			controlKeyIsDown = true;
		}
		
		return false;
	}
}
