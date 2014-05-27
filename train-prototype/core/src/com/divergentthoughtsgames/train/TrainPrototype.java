package com.divergentthoughtsgames.train;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.divergentthoughtsgames.train.graphics.CameraController;
import com.divergentthoughtsgames.train.graphics.Graphics;
import com.divergentthoughtsgames.train.graphics.KeyboardCameraController;

public class TrainPrototype extends ApplicationAdapter
{
	private Graphics graphics;
	private World world;
	
	@Override
	public void create()
	{
		graphics = new Graphics();
		world = new World();
		graphics.setWorld(world);
		
		CameraController kc = new KeyboardCameraController(graphics.getCamera());
		graphics.addCameraController(kc);
	}

	@Override
	public void render()
	{
		graphics.render();
		world.update();
	}
}
