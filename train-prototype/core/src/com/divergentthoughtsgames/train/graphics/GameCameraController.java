package com.divergentthoughtsgames.train.graphics;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class GameCameraController extends InputListener
{
	private int leftKey = Keys.LEFT;
	private int rightKey = Keys.RIGHT;
	private int upKey = Keys.UP;
	private int downKey = Keys.DOWN;
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;

	private float movementPerTick = 0.5f;
	
	private Camera camera;
	
	public GameCameraController(Camera camera)
	{
		super();
		
		this.camera = camera;
	}

	public void update()
	{
		if (moveLeft)
		{
			
		}
		camera.position.set(Math.round(tankX), Math.round(tankY), 0.f);
		camera.update();
	}
}
