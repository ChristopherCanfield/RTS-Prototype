/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.effect.ClickLocatorEffect;

public class ClickLocator extends InputAdapter
{
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		ClickLocatorEffect effect = new ClickLocatorEffect(screenX, screenY);
		App.graphics.addEffect(effect);
		return false;
	}
}
