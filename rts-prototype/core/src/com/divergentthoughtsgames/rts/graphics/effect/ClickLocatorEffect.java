/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.graphics.effect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.util.Coords;

public class ClickLocatorEffect extends AbstractEffect
{
	private static final float maxSize = 20;
	private static final float maxTime = 1.f;
	
	private int locationX;
	private int locationY;
	private double timeRemaining;
	
	public ClickLocatorEffect(int locationX, int locationY)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		this.timeRemaining = maxTime;
	}

	@Override
	public void render(ShapeRenderer renderer)
	{
		if (!isFinished())
		{
			float interpolation = Interpolation.circleIn.apply((float)(timeRemaining / maxTime));
			renderer.setColor(Color.NAVY);
			Vector3 adjustedLocation = Coords.screenToWorld(locationX, locationY);
			renderer.circle(adjustedLocation.x, adjustedLocation.y, maxSize * interpolation);
			
			timeRemaining -= App.getDeltaTime();
			if (timeRemaining < 0)
			{
				setFinished(true);
			}
		}
	}

	@Override
	protected void onFinished()
	{
	}
}
