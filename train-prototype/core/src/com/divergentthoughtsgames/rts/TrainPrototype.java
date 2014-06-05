package com.divergentthoughtsgames.rts;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.divergentthoughtsgames.rts.graphics.CameraController;
import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.graphics.KeyboardCameraController;
import com.divergentthoughtsgames.rts.input.DebugKeyProcessor;
import com.divergentthoughtsgames.rts.input.SelectionInputProcessor;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.entity.Footman;
import com.divergentthoughtsgames.rts.world.entity.Ogre;

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
