package com.divergentthoughtsgames.rts.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.SpriteRotator;
import com.divergentthoughtsgames.rts.graphics.Textures;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

public class Ogre extends Entity
{
	private TextureRegion stationaryNorth;
	private TextureRegion stationarySouth;
	private TextureRegion stationaryEast;
	private TextureRegion stationarySouthEast;
	private TextureRegion stationaryNorthEast;
	
	private SpriteRotator spriteRotator;
	private float rotation;
	
	private Animation currentAnimation;
	
	private float animationTime;

	public Ogre(World world)
	{
		super(world);
		
		rect = new Rectangle(300, 400, 40, 40);
		spriteOffsetX = -10;
		spriteOffsetY = 0;
		selectable = true;
		
		maxSpeed = 3.f;
		
		setAnimations();
		currentAnimation = spriteRotator.walkNorth;
		
		setOriginToCenter(300, 400);
	}
	
	private void setAnimations()
	{
		stationaryNorth = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 0, 64, 54);
		stationarySouth = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 301, 0, 64, 54);
		stationaryEast = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 155, 6, 64, 54);
		stationaryNorthEast = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 79, 0, 64, 56);
		stationarySouthEast = new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 229, 0, 64, 56);
		
		sprite = new Sprite(stationaryNorth);
		initializeSprite(sprite);
		
		spriteRotator = new SpriteRotator(sprite);
		
		spriteRotator.walkNorth = new Animation(0.095f, 
				stationaryNorth,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 78, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 149, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 226, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 5, 297, 64, 54));
		spriteRotator.walkNorth.setPlayMode(PlayMode.LOOP);
		
		spriteRotator.walkSouth = new Animation(0.095f,
				stationarySouth,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 301, 78, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 301, 149, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 301, 226, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 301, 297, 64, 54));
		spriteRotator.walkSouth.setPlayMode(PlayMode.LOOP);
		
		spriteRotator.walkEast = new Animation(0.095f,
				stationaryEast,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 155, 79, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 155, 150, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 155, 227, 64, 54),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 155, 298, 64, 54));
		spriteRotator.walkEast.setPlayMode(PlayMode.LOOP);
		
		spriteRotator.walkNorthEast = new Animation(0.095f,
				stationaryNorthEast,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 79, 79, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 79, 150, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 79, 227, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 79, 298, 64, 56));
		spriteRotator.walkNorthEast.setPlayMode(PlayMode.LOOP);
		
		spriteRotator.walkSouthEast = new Animation(0.095f,
				stationarySouthEast,
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 229, 79, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 229, 150, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 229, 227, 64, 56),
				new TextureRegion(App.graphics.getTexture(Textures.OrcOgre), 229, 298, 64, 56));
		spriteRotator.walkSouthEast.setPlayMode(PlayMode.LOOP);
	}

	@Override
	protected void onRotate()
	{
		currentAnimation = spriteRotator.onRotate();
		rotation = sprite.getRotation();
		rotation = sprite.getRotation() - (MathUtils.PI / 2.f * MathUtils.radiansToDegrees);
	}
	
	@Override
	protected float getRotation()
	{
		return rotation;
	}
	
	@Override
	protected void onDraw()
	{
		boolean flipX = sprite.isFlipX();
		sprite.setRegion(currentAnimation.getKeyFrame(animationTime));
		sprite.flip(flipX, false);
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
