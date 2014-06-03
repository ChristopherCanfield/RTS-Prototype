package com.divergentthoughtsgames.train.world;

import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.train.World;
import com.divergentthoughtsgames.train.util.Velocity;

/**
 * Base class for objects in the game world.
 * @author Christopher D. Canfield
 */
public abstract class Entity
{
	// The entity's unique id.
	private UUID id;
	
//	private ArrayMap<Class<? extends Component>, Component> components;
	
	// The list of controllers.
	private Array<Controller> controllers;
	
	/** The current sprite. **/
	protected Sprite sprite;
	/** The bounding rect's x offset from the sprite. **/
	protected float spriteOffsetX;
	/** The bounding rect's y offset from the sprite. **/
	protected float spriteOffsetY;
	
	protected Rectangle rect;
	
	protected Velocity velocity;
	
	protected boolean selectable;
	
	// Whether the entity has been disposed.
	private boolean disposed;
	
	/** The game world. **/
	protected final World world;
	
	protected Entity(World world)
	{
//		this.components = new ArrayMap<>(false, 4);
		this.controllers = new Array<>(false, 1);
		this.id = UUID.randomUUID();
		this.world = world;
		
//		setComponents();
//		setControllers();
	}
	
	/**
	 * Sets the entity's components. Called once, on construction.
	 */
//	protected abstract void setComponents();
	
	/**
	 * Sets the entity's controllers. Called once, on construction.
	 */
//	protected abstract void setControllers();
	
	public final UUID getId()
	{
		return id;
	}
	
	public final Rectangle getRect()
	{
		return rect;
	}
	
	public void setPosition(float x, float y)
	{
		rect.x = x;
		rect.y = y;
	}
	
//	protected void addComponent(Component component)
//	{
//		components.put(component.getClass(), component);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public <T extends Component> T get(Class<? extends Component> componentType)
//	{
//		return (T)components.get(componentType);
//	}
	
	protected void addController(Controller controller)
	{
		controllers.add(controller);
	}
	
//	protected void setSprite(Sprite sprite)
//	{
//		this.sprite = sprite;
//	}
	
	public final void draw(SpriteBatch batch)
	{
		onDraw();
		
		if (sprite != null)
		{
			sprite.setPosition(rect.x + spriteOffsetX, rect.y + spriteOffsetY);
			sprite.draw(batch);
		}
	}
	
	public final void drawRect(ShapeRenderer batch)
	{
		if (rect != null)
		{
			batch.rect(rect.x, rect.y, rect.width, rect.height);
		}
	}
	
	protected abstract void onDraw();
	
	
	public final void update()
	{
		onUpdate();
		
		for (final Controller c : controllers)
		{
			c.update(world);
		}
	}
	
	protected abstract void onUpdate();
	
	/**
	 * Disposes the entity.
	 */
	public final void dispose()
	{
		disposed = true;
		onDispose();
	}
	
	/**
	 * Called when the entity is disposed.
	 */
	protected abstract void onDispose();
	
	/**
	 * Specifies whether the entity has been disposed.
	 * @return true if the entity has been disposed.
	 */
	public final boolean isDisposed()
	{
		return disposed;
	}
}
