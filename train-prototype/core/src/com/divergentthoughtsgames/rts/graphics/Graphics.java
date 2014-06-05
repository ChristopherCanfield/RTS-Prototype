package com.divergentthoughtsgames.rts.graphics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.World;
import com.divergentthoughtsgames.rts.world.Entity;

public class Graphics
{
	private final Array<Sprite> sprites = new Array<>();
	private final SpriteBatch batch = new SpriteBatch();
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final HashMap<String, Texture> textures = new HashMap<>();
	
	private Camera camera;
	
	private final Array<CameraController> cameraControllers = new Array<CameraController>();
	
	private World world;
	
//	private Texture tempTexture;
	
	private float selectionRectStartX;
	private float selectionRectStartY;
	private float selectionRectEndX;
	private float selectionRectEndY;
	
	public Graphics()
	{
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
//		tempTexture = new Texture("badlogic.jpg");
//		Sprite sprite = new Sprite(tempTexture);
//		sprite.setPosition(0, 0);
//		addSprite(sprite);
		
		batch.getProjectionMatrix().setToOrtho2D(
				0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.position.set(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f, 0.f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}
	
	public void addCameraController(CameraController c)
	{
		cameraControllers.add(c);
	}
	
	public void setSelectionRectStart(float x, float y)
	{
		selectionRectStartX = x;
		selectionRectStartY = y;
	}
	
	public void setSelectionRectEnd(float x, float y)
	{
		selectionRectEndX = x;
		selectionRectEndY = y;
	}
	
	public void resetSelectionRect()
	{
		selectionRectStartX = selectionRectStartY = selectionRectEndX = selectionRectEndY = 0;
	}
	
	public Texture getTexture(String path)
	{
		Texture t = textures.get(path);
		if (t == null)
		{
			t = new Texture(path);
			textures.put(path, t);
		}
		return t;
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0.1f, 0.8f, 0.25f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for (CameraController c : cameraControllers)
		{
			c.update();
		}
		camera.update();
		batch.setProjectionMatrix(camera.combined);
//		shapeRenderer.setProjectionMatrix(camera.combined);
		
		drawSprites();
		drawPrimitives();
	}
	
	private void drawSprites()
	{
		batch.begin();
		
		for (Sprite sprite : sprites)
		{
			sprite.draw(batch);
		}
		
		world.draw(batch);
		
		batch.end();
	}
	
	private void drawPrimitives()
	{
		if (!(selectionRectStartX == 0 && selectionRectStartY == 0) &&
				!(selectionRectEndX == 0 && selectionRectEndY == 0))
		{
			shapeRenderer.begin(ShapeType.Line);
			
			shapeRenderer.rect(selectionRectStartX, selectionRectStartY,
					selectionRectEndX - selectionRectStartX, selectionRectEndY - selectionRectStartY);
			
			shapeRenderer.end();
		}
		
		Matrix4 originalProjMatrix = shapeRenderer.getProjectionMatrix().cpy();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		shapeRenderer.begin(ShapeType.Line);
		for (Entity e : world.getEntities())
		{
			e.drawRect(shapeRenderer);
		}
		shapeRenderer.end();
		
		shapeRenderer.setProjectionMatrix(originalProjMatrix);
	}
}