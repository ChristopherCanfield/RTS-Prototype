package com.divergentthoughtsgames.rts.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.World;
import com.divergentthoughtsgames.rts.graphics.Textures;
import com.divergentthoughtsgames.rts.world.Entity;

public class Footman extends Entity
{
	private TextureRegion stationaryNorth;
	
	private Animation attackNorth;
	
	private float animationTime;

	public Footman(World world)
	{
		super(world);
		
		rect = new Rectangle(300, 400, 40, 40);
		spriteOffsetX = -13;
		spriteOffsetY = -8;
		selectable = true;
		
		setAnimations();
	}
	
	private void setAnimations()
	{
		stationaryNorth = new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 0, 57, 58);
		sprite = new Sprite(stationaryNorth);
		setPosition(300, 400);
		initializeSprite(sprite);
		
		attackNorth = new Animation(0.095f, 
				stationaryNorth,
				new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 0, 57, 58),
				new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 276, 57, 58),
				new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 324, 57, 69),
				new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 397, 57, 55),
				new TextureRegion(App.graphics.getTexture(Textures.HumanFootman), 2, 451, 57, 58));
		attackNorth.setPlayMode(PlayMode.LOOP);		
	}

	@Override
	protected void onDraw()
	{
		sprite.setRegion(attackNorth.getKeyFrame(animationTime));
	}

	@Override
	protected void onUpdate()
	{
		animationTime += Gdx.graphics.getRawDeltaTime();
	}

	@Override
	protected void onDispose()
	{
	}
}
