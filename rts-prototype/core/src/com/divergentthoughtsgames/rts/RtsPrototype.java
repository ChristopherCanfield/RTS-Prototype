package com.divergentthoughtsgames.rts;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.divergentthoughtsgames.rts.graphics.CameraController;
import com.divergentthoughtsgames.rts.graphics.Graphics;
import com.divergentthoughtsgames.rts.input.DebugKeyProcessor;
import com.divergentthoughtsgames.rts.input.KeyboardCameraController;
import com.divergentthoughtsgames.rts.input.MouseCameraController;
import com.divergentthoughtsgames.rts.input.SelectionInputProcessor;
import com.divergentthoughtsgames.rts.input.UnitControlInputProcessor;
import com.divergentthoughtsgames.rts.input.UnitGroupKeyProcessor;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;
import com.divergentthoughtsgames.rts.world.entity.Footman;
import com.divergentthoughtsgames.rts.world.entity.HumanFarm;
import com.divergentthoughtsgames.rts.world.entity.HumanTownCenter;
import com.divergentthoughtsgames.rts.world.entity.Ogre;

public class RtsPrototype extends ApplicationAdapter
{	
	@Override
	public void create()
	{
		Graphics graphics = new Graphics();
		World world = new World();
		graphics.setWorld(world);
		
		CameraController kcc = new KeyboardCameraController((OrthographicCamera)graphics.getCamera());
		graphics.addCameraController(kcc);
		
		setInputProcessors(graphics);
		
		App.setGraphics(graphics);
		App.setWorld(world);
		
		world.add(new Ogre(world));
		
		Entity ogre = new Ogre(world);
		ogre.setPosition(0, 0);
		world.add(ogre);
		
		ogre = new Ogre(world);
		ogre.setPosition(2000, 2000);
		world.add(ogre);
		
		Entity ogre2 = new Ogre(world);
		ogre2.setPosition(100, 250);
		world.add(ogre2);
		
		Entity ogre3 = new Ogre(world);
		ogre3.setPosition(600, 450);
		world.add(ogre3);
		
		Entity footman1 = new Footman(world);
		footman1.setPosition(225, 500);
		world.add(footman1);
		
		Entity humanFarm = new HumanFarm(world);
		humanFarm.setPosition(700, 550);
		world.add(humanFarm);
		
		humanFarm = new HumanFarm(world);
		humanFarm.setPosition(636, 550);
		world.add(humanFarm);
		
		humanFarm = new HumanFarm(world);
		humanFarm.setPosition(636, 614);
		world.add(humanFarm);
		
		Entity humanTownCenter = new HumanTownCenter(world);
		humanTownCenter.setPosition(850, 600);
		world.add(humanTownCenter);
		
		// For debugging.
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		App.setDebug(true);
	}
	
	private static void setInputProcessors(Graphics graphics)
	{
		InputMultiplexer inputProcessors = new InputMultiplexer();
		inputProcessors.addProcessor(new SelectionInputProcessor(graphics));
		inputProcessors.addProcessor(new UnitGroupKeyProcessor());
		inputProcessors.addProcessor(new UnitControlInputProcessor());
		inputProcessors.addProcessor(new MouseCameraController((OrthographicCamera)graphics.getCamera()));
		inputProcessors.addProcessor(new DebugKeyProcessor());
		Gdx.input.setInputProcessor(inputProcessors);
	}

	@Override
	public void render()
	{
		App.addGameTime(Gdx.graphics.getRawDeltaTime());
		App.graphics.render();
		App.world.update();
	}
}
