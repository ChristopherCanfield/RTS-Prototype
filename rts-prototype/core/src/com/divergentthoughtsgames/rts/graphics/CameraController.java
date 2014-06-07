package com.divergentthoughtsgames.rts.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.divergentthoughtsgames.rts.App;

public abstract class CameraController
{
	private OrthographicCamera camera;

	protected CameraController(OrthographicCamera camera)
	{
		this.camera = camera;
	}
	
	protected void move(float xOffset, float yOffset)
	{
		// TODO (6/7/2014): adjust to take into account zoom.
		
		if (xOffset != 0)
		{
//			camera.translate(xOffset, 0.f, 0.f);
			camera.position.x = calculateCameraX(camera, xOffset);
		}
		
		if (yOffset != 0)
		{
//			camera.translate(0.f, yOffset, 0.f);
			camera.position.y = calculateCameraY(camera, yOffset);
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
	
	private static float calculateCameraX(Camera camera, float xOffset)
	{
		System.out.println("Camera x: " + camera.position.x);
		System.out.println("Camera width: " + camera.viewportWidth / 2.f);
		
		float newCameraX = 0;
		if ((camera.position.x - camera.viewportWidth / 2.f) <= 0 && xOffset < 0)
		{
			newCameraX = camera.viewportWidth / 2.f;
		}
		else if (camera.position.x + camera.viewportWidth / 2.f >= App.world.getWidth() &&
				xOffset > 0)
		{
			newCameraX = App.world.getWidth() - camera.viewportWidth / 2.f;
		}
		else
		{
			newCameraX = camera.position.x + xOffset;
		}

		return newCameraX;
	}

	private static float calculateCameraY(Camera camera, float yOffset)
	{
		float newCameraY = 0;
		if ((camera.position.y - camera.viewportHeight / 2.f) <= 0 && yOffset < 0)
		{
			newCameraY = camera.viewportHeight / 2.f;
		}
		else if (camera.position.y + camera.viewportHeight / 2.f >= App.world.getHeight() &&
				yOffset > 0)
		{
			newCameraY = App.world.getHeight() - camera.viewportHeight / 2.f;
		}
		else
		{
			newCameraY = camera.position.y + yOffset;
		}

		return newCameraY;
	}
}
