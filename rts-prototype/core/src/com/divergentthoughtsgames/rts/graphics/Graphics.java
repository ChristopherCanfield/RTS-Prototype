package com.divergentthoughtsgames.rts.graphics;

import java.util.HashMap;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.effect.Effect;
import com.divergentthoughtsgames.rts.nav.Edge;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

public class Graphics
{
	// Sprites that are not directly owned by an entity.
	private final Array<Sprite> sprites = new Array<>();
	private final Array<Effect> effects = new Array<>();

	private final SpriteBatch batch = new SpriteBatch();
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final HashMap<String, Texture> textures = new HashMap<>();

	private OrthographicCamera camera;
	private final Array<CameraController> cameraControllers = new Array<CameraController>();

	private World world;

	// Variables for the selection box.
	private float selectionRectStartX;
	private float selectionRectStartY;
	private float selectionRectEndX;
	private float selectionRectEndY;

	public Graphics()
	{
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

	public float getZoom()
	{
		return camera.zoom;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}

	public void addEffect(Effect effect)
	{
		effects.add(effect);
	}

	public void addCameraController(CameraController c)
	{
		cameraControllers.add(c);
	}

	public void setSelectionRectStart(float screenX, float screenY)
	{
		selectionRectStartX = screenX;
		selectionRectStartY = screenY;
	}

	public void setSelectionRectEnd(float screenX, float screenY)
	{
		selectionRectEndX = screenX;
		selectionRectEndY = screenY;
	}

	public void resetSelectionRect()
	{
		selectionRectStartX = selectionRectStartY = selectionRectEndX = selectionRectEndY = 0;
	}

//	public void setProjectionMatrix(Matrix4 projectionMatrix)
//	{
//		batch.setProjectionMatrix(projectionMatrix);
//		shapeRenderer.setProjectionMatrix(projectionMatrix);
//	}

//	public float getZoom()
//	{
//		return camera.zoom;
//	}

	/**
	 * Returns a reference to the specified texture. The texture is loaded if necessary.
	 * @param path the path to the texture.
	 * @return a reference to the specified texture.
	 */
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

	/**
	 * Draws the specified path.
	 * @param nextNode the first node.
	 * @param path a queue of nodes that make up the path.
	 */
	public void drawPath(Node nextNode, Queue<Node> path)
	{
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.YELLOW);
		Node previous = nextNode;

		for (Node node : path)
		{
			for(Edge edge : node.getEdges())
			{
				if (edge.getOppositeNode(node).equals(previous))
				{
					edge.draw(shapeRenderer);
				}
			}
			previous = node;
		}
		shapeRenderer.end();
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

		setClipBounds();

		if (App.debug.isNavGraphVisible())
		{
			shapeRenderer.begin(ShapeType.Line);
			world.drawNavGraph(shapeRenderer);
			shapeRenderer.end();
		}

		drawSprites();
		drawPrimitives();

		ScissorStack.popScissors();
	}

	private void setClipBounds()
	{
		final float adjustedCameraX = camera.position.x - camera.viewportWidth / 2.f * camera.zoom;
		final float adjustedCameraY = camera.position.y - camera.viewportHeight / 2.f * camera.zoom;

		Rectangle scissors = new Rectangle();
		Rectangle clipBounds = new Rectangle(adjustedCameraX, adjustedCameraY,
				camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
		ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
		ScissorStack.pushScissors(scissors);
	}

	private void drawSprites()
	{
		batch.begin();

		for (final Sprite sprite : sprites)
		{
			sprite.draw(batch);
		}

		world.draw(batch);

		batch.end();
		batch.flush();
	}

	private void drawPrimitives()
	{
		Matrix4 originalProjMatrix = shapeRenderer.getProjectionMatrix().cpy();
		shapeRenderer.setProjectionMatrix(camera.combined);

		shapeRenderer.setColor(Color.WHITE);
		if (!(selectionRectStartX == 0 && selectionRectStartY == 0) &&
				!(selectionRectEndX == 0 && selectionRectEndY == 0))
		{
			shapeRenderer.begin(ShapeType.Line);

			shapeRenderer.rect(selectionRectStartX, selectionRectStartY,
					selectionRectEndX - selectionRectStartX, selectionRectEndY - selectionRectStartY);

			shapeRenderer.end();
		}

		shapeRenderer.begin(ShapeType.Line);
		for (Entity e : world.getEntities())
		{
			e.drawRect(shapeRenderer);
		}
		drawEffects(shapeRenderer, effects);
		shapeRenderer.end();

		shapeRenderer.setProjectionMatrix(originalProjMatrix);
	}

	private static void drawEffects(ShapeRenderer renderer, Array<Effect> effects)
	{
		Array<Effect> finished = new Array<Effect>();

		for (Effect effect : effects)
		{
			effect.render(renderer);

			if (effect.isFinished())
			{
				finished.add(effect);
			}
		}

		effects.removeAll(finished, true);
	}
}
