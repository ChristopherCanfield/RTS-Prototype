package com.divergentthoughtsgames.train.graphics;

import com.badlogic.gdx.graphics.Camera;

public abstract class CameraController
{
	private Camera camera;

	protected CameraController(Camera camera)
	{
		this.camera = camera;
	}
	
	protected void moveCamera(float xOffset, float offsetY)
	{
	// Ensure the camera stays within the world boundaries. This example world is
	// 2024 pixels wide, and 2024 pixels tall.
	if (xOffset != 0)
	{
		camera.translate(xOffset, 0.f, 0.f);
	}
	
	if (offsetY != 0)
	{
		camera.translate(0.f, offsetY, 0.f);
	}
	}
	
	public abstract void update();
}
