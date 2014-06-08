/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.graphics;

import static com.divergentthoughtsgames.rts.util.GameMath.between;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteRotator
{
	public Animation walkNorth;
	public Animation walkSouth;
	public Animation walkEast;
	public Animation walkSouthEast;
	public Animation walkNorthEast;
	public Animation walkSouthWest;
	public Animation walkNorthWest;
	
	private final Sprite sprite;
	
	public SpriteRotator(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public Animation onRotate()
	{
		float rotation = sprite.getRotation();
		if (rotation < 0)
		{
			rotation += 360;
		}
		else if (rotation > 360)
		{
			rotation -= 360;
		}
		sprite.setRotation(0);
		
		Animation animation = between(rotation, 157.5f, 202.5f) ? walkSouth :
				between(rotation, 202.5f, 247.5f) ? walkSouthEast :
				between(rotation, 247.5f, 292.5f) ? walkEast :
				between(rotation, 292.5f, 337.5f) ? walkNorthEast :
				(between(rotation, 337.5f, 360.f) || between(rotation, 0, 22.5f)) ? walkNorth :
					null;
	
		if (animation == null)
		{
			if (between(rotation, 22.5f, 67.5f))
			{ 
				animation = walkNorthEast;
			}
			else if (between(rotation, 67.5f, 112.5f))
			{
				animation = walkEast;
			}
			else if (between(rotation, 112.5f, 157.5f)) 
			{
				animation = walkSouthEast;
			}
			
			if (!sprite.isFlipX()) sprite.flip(true, false);
		}
		else if (sprite.isFlipX())
		{
			sprite.flip(true, false);
		}
		
		return animation;
	}
}
