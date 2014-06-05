package com.divergentthoughtsgames.rts.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.divergentthoughtsgames.rts.graphics.CameraController;

public class MouseCameraController extends CameraController implements InputProcessor
{

	public MouseCameraController(OrthographicCamera camera)
	{
		super(camera);
	}

	@Override
	public void update()
	{
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		final float zoomFactor = (amount > 0) ? 1.05f : 0.95f;
		zoom(zoomFactor);
		
		return false;
	}

}
