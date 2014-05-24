package com.divergentthoughtsgames.train.graphics;

import com.badlogic.gdx.utils.Array;

public class Graphics
{
	private Array<Sprite> sprites = new Array<>();
	
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}
	
	public void render()
	{
		throw new UnsupportedOperationException();
	}
}
