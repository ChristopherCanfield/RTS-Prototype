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

public class HumanFarm extends Entity
{
	private TextureRegion built;
	private TextureRegion building;
	
	private Animation buildingAnimation;
	
	private float buildAnimationTime;

	public HumanFarm(World world)
	{
		super(world);
		
		rect = new Rectangle(0, 0, 64, 64);
		spriteOffsetX = 0;
		spriteOffsetY = 0;
		selectable = false;
		
		setAnimations();
	}
	
	private void setAnimations()
	{
		built = new TextureRegion(App.graphics.getTexture(Textures.HumanBuildingsSummer), 398, 3, 64, 64);
		building = new TextureRegion(App.graphics.getTexture(Textures.HumanBuildingsSummer), 398, 68, 64, 64);
		
		sprite = new Sprite(built);
		initializeSprite(sprite);
		
		buildingAnimation = new Animation(6.f, 
				building,
				built);
		buildingAnimation.setPlayMode(PlayMode.NORMAL);		
	}

	@Override
	protected void onDraw()
	{
//		sprite.setRegion(buildingAnimation.getKeyFrame(animationTime));
	}

	@Override
	protected void onUpdate()
	{
		buildAnimationTime += Gdx.graphics.getRawDeltaTime();
	}

	@Override
	protected void onDispose()
	{
	}
}
