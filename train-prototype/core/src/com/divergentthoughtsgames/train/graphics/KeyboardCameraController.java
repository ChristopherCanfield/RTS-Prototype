package com.divergentthoughtsgames.train.graphics;

import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class KeyboardCameraController extends CameraController
{
	private float movementPerTick = 5.f;
	
	public KeyboardCameraController(Camera camera)
	{
		super(camera);
	}

	public void update()
	{
		if (Gdx.input.isKeyPressed(Keys.LEFT))
		{
			moveCamera(-movementPerTick, 0.f);
		}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			moveCamera(movementPerTick, 0.f);
		}

		if (Gdx.input.isKeyPressed(Keys.UP))
		{
			moveCamera(0.f, movementPerTick);
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN))
		{
			moveCamera(0.f, -movementPerTick);
		}
	}
}
