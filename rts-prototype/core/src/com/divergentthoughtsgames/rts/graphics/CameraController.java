package com.divergentthoughtsgames.rts.graphics;

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
			camera.position.x = calculateCameraX(camera, xOffset);
		}
		
		if (yOffset != 0)
		{
			camera.position.y = calculateCameraY(camera, yOffset);
		}
	}
	
	protected void zoom(float zoomFactor)
	{
		final float maxZoomWidth = App.world.getWidth() / camera.viewportWidth;
		final float maxZoomHeight = App.world.getHeight() / camera.viewportHeight;
		final float newZoom = camera.zoom * zoomFactor;
		
		if (newZoom > maxZoomWidth)
		{
			camera.zoom = maxZoomWidth;
		}
		else if (newZoom > maxZoomHeight)
		{
			camera.zoom = maxZoomHeight;
		}
		else
		{
			camera.zoom *= zoomFactor;
		}
		
		move(-0.0001f, -0.0001f);
		move(0.0001f, 0.0001f);
	}
	
	protected void resetZoom()
	{
		camera.zoom = 1.f;
	}
	
	public abstract void update();
	
	private static float calculateCameraX(OrthographicCamera camera, float xOffset)
	{
		System.out.println("Camera x: " + camera.position.x);
		System.out.println("Camera width: " + camera.viewportWidth / 2.f);
		
		float newCameraX = 0;
		final float cameraHalfWidth = camera.viewportWidth / 2.f * camera.zoom;
		if ((camera.position.x - cameraHalfWidth) <= 0 && xOffset < 0)
		{
			newCameraX = cameraHalfWidth;
		}
		else if (camera.position.x + cameraHalfWidth >= App.world.getWidth() &&
				xOffset > 0)
		{
			newCameraX = App.world.getWidth() - cameraHalfWidth;
		}
		else
		{
			newCameraX = camera.position.x + xOffset;
		}

		return newCameraX;
	}

	private static float calculateCameraY(OrthographicCamera camera, float yOffset)
	{
		float newCameraY = 0;
		final float cameraHalfHeight = camera.viewportHeight / 2.f * camera.zoom;
		if ((camera.position.y - cameraHalfHeight) <= 0 && yOffset < 0)
		{
			newCameraY = cameraHalfHeight;
		}
		else if (camera.position.y + cameraHalfHeight >= App.world.getHeight() &&
				yOffset > 0)
		{
			newCameraY = App.world.getHeight() - cameraHalfHeight;
		}
		else
		{
			newCameraY = camera.position.y + yOffset;
		}

		return newCameraY;
	}
}
