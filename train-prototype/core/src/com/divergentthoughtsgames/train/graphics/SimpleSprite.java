package com.divergentthoughtsgames.train.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SimpleSprite implements GameSprite
{
	private final Sprite sprite;
	
	public SimpleSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
}
