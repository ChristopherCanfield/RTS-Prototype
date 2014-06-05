package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.divergentthoughtsgames.rts.graphics.CameraController;

public class KeyboardCameraController extends CameraController
{
	private static final float movementPerTick = 5.f;
	private static final float fastMovementPerTick = 15.f;
	
	public KeyboardCameraController(OrthographicCamera camera)
	{
		super(camera);
	}

	@Override
	public void update()
	{
		final Input in = Gdx.input;
		float cameraTranslateSpeed = movementPerTick;
		if (in.isKeyPressed(Keys.SHIFT_LEFT) || in.isKeyPressed(Keys.SHIFT_RIGHT))
		{
			cameraTranslateSpeed = fastMovementPerTick;
		}
		
		if (in.isKeyPressed(Keys.LEFT))
		{
			move(-cameraTranslateSpeed, 0.f);
		}
		else if (in.isKeyPressed(Keys.RIGHT))
		{
			move(cameraTranslateSpeed, 0.f);
		}

		if (in.isKeyPressed(Keys.UP))
		{
			move(0.f, cameraTranslateSpeed);
		}
		else if (in.isKeyPressed(Keys.DOWN))
		{
			move(0.f, -cameraTranslateSpeed);
		}
		
		if (in.isKeyPressed(Keys.UP) && 
				(in.isKeyPressed(Keys.CONTROL_LEFT) || in.isKeyPressed(Keys.CONTROL_RIGHT)))
		{
			zoom(0.95f);
		}
		else if (in.isKeyPressed(Keys.DOWN) &&
				(in.isKeyPressed(Keys.CONTROL_LEFT) || in.isKeyPressed(Keys.CONTROL_RIGHT)))
		{
			zoom(1.05f);
		}
		
		if (in.isKeyPressed(Keys.BACKSPACE))
		{
			resetZoom();
		}
				
	}
}
