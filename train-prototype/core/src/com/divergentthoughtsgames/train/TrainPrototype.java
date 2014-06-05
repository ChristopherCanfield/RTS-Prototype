package com.divergentthoughtsgames.train;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.divergentthoughtsgames.train.graphics.CameraController;
import com.divergentthoughtsgames.train.graphics.Graphics;
import com.divergentthoughtsgames.train.graphics.KeyboardCameraController;
import com.divergentthoughtsgames.train.input.DebugKeyProcessor;
import com.divergentthoughtsgames.train.input.SelectionInputProcessor;
import com.divergentthoughtsgames.train.world.Entity;
import com.divergentthoughtsgames.train.world.entity.Footman;
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
		inputProcessors.addProcessor(new DebugKeyProcessor());
		Gdx.input.setInputProcessor(inputProcessors);
		
		App.graphics = graphics;
		App.world = world;
		
		world.add(new Ogre(world));
		
		Entity ogre2 = new Ogre(world);
		ogre2.setPosition(100, 250);
		world.add(ogre2);
		
		Entity ogre3 = new Ogre(world);
		ogre3.setPosition(600, 450);
		world.add(ogre3);
		
		Entity footman1 = new Footman(world);
		footman1.setPosition(225, 500);
		world.add(footman1);
		
		// For debugging.
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		App.debug = true;
	}

	@Override
	public void render()
	{
		graphics.render();
		world.update();
	}
}
