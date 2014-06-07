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
		
		float cameraX = camera.position.x - camera.viewportWidth / 2.f;
		if (cameraX <= 0 && xOffset < 0)
		{
			cameraX = camera.viewportWidth / 2.f;
		}
		// TODO (6/7/2014): Restrict to world width.
		else
		{
			cameraX = camera.position.x + xOffset;
		}
//		else if (cameraX > App.world.getWidth() - camera.viewportWidth / 2.f)
//		{
//			// Ensure that screen doesn't go negative if the world is smaller than the camera.
//			float newCameraX = App.world.getWidth() - camera.viewportWidth / 2.f;
//			cameraX = (newCameraX >= 0) ? newCameraX : 0;
//		}

		return cameraX;
	}

	private static float calculateCameraY(Camera camera, float yOffset)
	{
		float cameraY = camera.position.y + yOffset;
		if (cameraY < 0)
		{
			cameraY = 0;
		}
		else if (cameraY > App.world.getHeight() - camera.viewportHeight)
		{
			// Ensure that screen doesn't go negative if the world is smaller than the camera.
			float newCameraY = App.world.getHeight() - camera.viewportHeight;
			cameraY = (newCameraY >= 0) ? newCameraY : 0;
		}

		return cameraY;
	}
}
