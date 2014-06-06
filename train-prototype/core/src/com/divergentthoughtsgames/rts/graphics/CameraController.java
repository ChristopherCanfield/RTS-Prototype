package com.divergentthoughtsgames.rts.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class CameraController
{
	private OrthographicCamera camera;

	protected CameraController(OrthographicCamera camera)
	{
		this.camera = camera;
	}
	
	protected void move(float xOffset, float offsetY)
	{
		if (xOffset != 0)
		{
			camera.translate(xOffset, 0.f, 0.f);
		}
		
		if (offsetY != 0)
		{
			camera.translate(0.f, offsetY, 0.f);
		}
	}
	
	protected void zoom(float zoomFactor)
	{
		camera.zoom *= zoomFactor;
	}
	
	protected void resetZoom()
	{
		camera.zoom = 1.f;
	}
	
	public abstract void update();
}
