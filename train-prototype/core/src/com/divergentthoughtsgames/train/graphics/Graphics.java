package com.divergentthoughtsgames.train.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Graphics
{
	private Array<Sprite> sprites = new Array<>();
	private SpriteBatch batch = new SpriteBatch();
	
	private Camera camera;
	
	private Texture tempTexture;
	
	public Graphics(int windowWidth, int windowHeight)
	{
		camera = new OrthographicCamera(windowWidth, windowHeight);
		tempTexture = new Texture("badlogic.jpg");
		addSprite(new Sprite(tempTexture));
	}
	
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		for (Sprite sprite : sprites)
		{
			sprite.draw(batch);
		}
		
		batch.end();
	}
}
