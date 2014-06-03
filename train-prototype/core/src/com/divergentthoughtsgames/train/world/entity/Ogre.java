package com.divergentthoughtsgames.train.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.train.App;
import com.divergentthoughtsgames.train.World;
import com.divergentthoughtsgames.train.graphics.Textures;
import com.divergentthoughtsgames.train.world.Entity;

public class Ogre extends Entity
{
	private TextureRegion stationaryNorth;
	private TextureRegion stationarySouth;
	private TextureRegion stationaryEast;
	private TextureRegion stationaryWest;
	
	private Animation walkNorth;
	
	private float animationTime;

	public Ogre(World world)
	{
		super(world);
		
		rect = new Rectangle(300, 400, 40, 40);
		selectable = true;
		
		setAnimations();
	}
	
	private void setAnimations()
	{
		stationaryNorth = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 0, 64, 54);
		sprite = new Sprite(stationaryNorth);
		sprite.setPosition(300, 400);
		
		walkNorth = new Animation(0.095f, 
				stationaryNorth,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 78, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 149, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 226, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 297, 64, 54));
		walkNorth.setPlayMode(PlayMode.LOOP);		
	}

	@Override
	protected void onDraw()
	{
		sprite.setRegion(walkNorth.getKeyFrame(animationTime));
	}

	@Override
	protected void onUpdate()
	{
		animationTime += Gdx.graphics.getRawDeltaTime();
		System.out.println("T");
	}

	@Override
	protected void onDispose()
	{
	}


	
}
