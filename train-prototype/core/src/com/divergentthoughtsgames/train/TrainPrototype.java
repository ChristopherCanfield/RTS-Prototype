package com.divergentthoughtsgames.train;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.divergentthoughtsgames.train.graphics.CameraController;
import com.divergentthoughtsgames.train.graphics.Graphics;
import com.divergentthoughtsgames.train.graphics.KeyboardCameraController;
import com.divergentthoughtsgames.train.input.SelectionInputProcessor;
import com.divergentthoughtsgames.train.world.entity.Ogre;

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
		
		CameraController kcc = new KeyboardCameraController(graphics.getCamera());
		graphics.addCameraController(kcc);
		
		InputMultiplexer inputProcessors = new InputMultiplexer();
		inputProcessors.addProcessor(new SelectionInputProcessor(graphics));
		Gdx.input.setInputProcessor(inputProcessors);
		
		App.graphics = graphics;
		App.world = world;
		
		world.add(new Ogre(world));
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render()
	{
		graphics.render();
		world.update();
	}
}
