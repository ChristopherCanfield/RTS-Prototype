package com.divergentthoughtsgames.rts.world;

import java.util.Objects;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.util.GameMath;
import com.divergentthoughtsgames.rts.world.command.EntityCommand;
import com.divergentthoughtsgames.rts.world.command.NullCommand;

/**
 * Base class for objects in the game world.
 * @author Christopher D. Canfield
 */
public abstract class Entity
{
	// The entity's unique id.
	private UUID id;
	
	// The list of controllers.
	private Array<Controller> controllers;
	
	/** The current sprite. **/
	protected Sprite sprite;
	/** The bounding rect's x offset from the sprite. **/
	protected float spriteOffsetX;
	/** The bounding rect's y offset from the sprite. **/
	protected float spriteOffsetY;
	
	/** The entity's bounding rectangle. **/
	protected Rectangle rect;
	
	/** The entity's max speed. **/
	protected float maxSpeed;
	
	// The entity's current speed.
	private float speed;
	
	private final Vector2 movementVector = new Vector2();
	
	/** Whether the entity is selectable by the user. **/
	protected boolean selectable;
	
	// Whether the entity has been disposed.
	private boolean disposed;
	
	/** The game world. **/
	protected final World world;
	
	private EntityCommand command;
	
	protected Entity(World world)
	{
		this.controllers = new Array<>(false, 1);
		this.id = UUID.randomUUID();
		this.world = world;
		this.command = NullCommand.get();
		
//		setControllers();
	}
	
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
	
	public final boolean contains(int x, int y)
	{
		return rect.contains(x, y);
	}
	
//	public final boolean containsAtCenter(int x, int y)
//	{
//		if (centerRect == null)
//		{
//			centerRect = new Rectangle(x + rect.width / 2.f, y + rect.width / 2.f, 3, 3);
//		}
//		centerRect.x = rect.x + rect.width / 2.f;
//		centerRect.y = rect.y + rect.height / 2.f;
//		return centerRect.contains(x, y);
//	}
	
	public final Node getNode()
	{
		return Find.node(this);
	}
	
	public final void setPosition(float x, float y)
	{
		rect.x = x;
		rect.y = y;
		onMove();
	}
	
	public void move()
	{
		rect.x += speed * movementVector.x;
		rect.y += speed * movementVector.y;
		onMove();
		
		if (App.debugEnabled())
		{
			Gdx.app.debug("Entity Position", toString());
		}
	}
	
	protected void onMove()
	{
	}
	
	public final int getX()
	{
		return (int)rect.x;
	}
	
	public final int getY()
	{
		return (int)rect.y;
	}
	
	public final float getSpeed()
	{
		return speed;
	}
	
	/**
	 * Sets the entity's speed to its maximum.
	 */
	public final void setSpeedMax()
	{
		speed = maxSpeed;
	}
	
	public final void setSpeed(float change)
	{
		speed += change;
		if (speed < 0)
		{
			speed = 0;
		}
		else if (speed > maxSpeed)
		{
			speed = maxSpeed;
		}
	}
	
	public final void rotateToFace(int x, int y)
	{
		float angle = GameMath.angleToFace((int)rect.x, (int)rect.y, x, y) * MathUtils.radiansToDegrees;
		sprite.setRotation(angle + (MathUtils.PI / 2.f * MathUtils.radiansToDegrees));
		
		onRotate(angle);
		setMovementVector();
	}
	
	public final void rotateToFace(float x, float y)
	{
		rotateToFace((int)x, (int)y);
	}
	
	private void setMovementVector()
	{
		float angle = getRotation() * MathUtils.degreesToRadians;
		Gdx.app.debug("Entity movement vector rotation", "Radians: " + angle);
		Gdx.app.debug("Entity movement vector rotation", "Degrees: " + getRotation());
		movementVector.x = (float)-Math.cos(angle);
		movementVector.y = (float)-Math.sin(angle);
	}
	
	/**
	 * Provides a hook into the rotateToFace method. Override this to receive notification when
	 * the entity is rotated.
	 */
	protected void onRotate(@SuppressWarnings("unused") float rotation)
	{
	}
	
	public final void logRotation()
	{
		Gdx.app.debug("Entity Rotation", toString() + ": " + getRotation());
	}
	
	protected float getRotation()
	{
		return sprite.getRotation();
	}
	
	protected final void addController(Controller controller)
	{
		controllers.add(controller);
	}
	
	public final void setCommand(EntityCommand c)
	{
		command = c;
	}
	
	protected static void initializeSprite(Sprite sprite)
	{
		sprite.setOriginCenter();
	}
	
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
			boolean isSelected = App.selected.contains(this);
			Color originalColor = null;
			if (isSelected)
			{
				originalColor = batch.getColor().cpy();
				batch.setColor(Color.BLUE);
			}
			
			if (isSelected || App.debugEnabled())
			{
				batch.rect(rect.x, rect.y, rect.width, rect.height);
			}
			
			if (isSelected)
			{
				batch.setColor(originalColor);
			}
		}
	}
	
	protected abstract void onDraw();
	
	public final void update()
	{
		onUpdate();
		
		if (!command.isFinished())
		{
			command.update();
		}
		
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
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[Entity: ")
			.append(getId())
			.append("; x: ").append(getX()).append(", y: ").append(getY())
			.append("; movement-vector-x: ").append(movementVector.x)
			.append(", movement-vector-y: ").append(movementVector.y)
			.append("; rotation: ").append(getRotation())
			.append("; disposed: ").append(disposed)
			.append("]");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Entity))
		{
			return false;
		}
		Entity other = (Entity)o;
		return (other == this || other.id == id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(id);
	}
}
