package com.divergentthoughtsgames.train;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.divergentthoughtsgames.train.graphics.Graphics;

public class TrainPrototype extends ApplicationAdapter
{
	private Graphics graphics;
	
	private int windowWidth;
	private int windowHeight;

	@Override
	public void create()
	{
		graphics = new Graphics(windowWidth, windowHeight);
	}

	@Override
	public void render()
	{
		graphics.render();
	}
}
