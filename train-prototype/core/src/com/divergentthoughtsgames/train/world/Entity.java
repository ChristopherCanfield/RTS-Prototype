package com.divergentthoughtsgames.train.world;

import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	// The current sprite.
	protected Sprite sprite;
	
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
	
	public UUID getId()
	{
		return id;
	}
	
	public Rectangle getRect()
	{
		return rect;
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
	
	public void draw(SpriteBatch batch)
	{
		onDraw();
		
		if (sprite != null)
		{
			sprite.draw(batch);
		}
	}
	
	protected abstract void onDraw();
	
	
	public void update()
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
	public void dispose()
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
	public boolean isDisposed()
	{
		return disposed;
	}
}
