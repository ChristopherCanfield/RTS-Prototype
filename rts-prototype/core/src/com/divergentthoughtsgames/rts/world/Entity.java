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
//	protected Rectangle centerRect;

	/** The entity's max speed. **/
	protected float maxSpeed;

	/** The entity's deceleration rate. **/
	protected float decelerationRate;

	/** The entity's acceleration rate. **/
	protected float accelerationRate;

	// The entity's current speed.
	private float speed;

	private final Vector2 movementVector = new Vector2();

	/** Specifies whether the entity is solid. Solid entities can't overlap. **/
	protected boolean solid;
	/** Specified whether the entity can move. **/
	protected boolean moveable;

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

		setControllers();
	}

	/**
	 * Sets the entity's controllers. Called once, on construction.
	 */
	protected abstract void setControllers();

	public final UUID getId()
	{
		return id;
	}

	public final boolean isSolid()
	{
		return solid;
	}

	public final boolean isMoveable()
	{
		return moveable;
	}

	public final Rectangle getRect()
	{
		return rect;
	}

	public final boolean contains(int x, int y)
	{
		return rect.contains(x, y);
	}

	public final boolean contains(float x, float y)
	{
		return rect.contains(x, y);
	}

//	public final boolean containsAtCenter(int x, int y)
//	{
//		if (centerRect == null)
//		{
//			centerRect = new Rectangle(x + rect.width / 2.f, y + rect.height / 2.f, 16, 16);
//		}
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
//		if (centerRect != null)
//		{
//			centerRect.x = x + rect.width / 2.f;
//			centerRect.y = y + rect.height / 2.f;
//		}
		onMove();
	}

	public void move()
	{
		if (App.possessedEntity != this)
		{
			speed = maxSpeed;
			move(speed * movementVector.x, speed * movementVector.y);
		}
	}

	public void move(float x, float y)
	{
		rect.x += x;
		rect.y += y;
//		if (centerRect != null)
//		{
//			centerRect.x = rect.x + rect.width / 2.f;
//			centerRect.y = rect.y + rect.height / 2.f;
//		}

		onMove();
		processMove();
	}

	public void moveNorth()
	{
		move(0, maxSpeed);
	}

	public void moveSouth()
	{
		move(0, -maxSpeed);
	}

	public void moveEast()
	{
		move(maxSpeed, 0);
	}

	public void moveWest()
	{
		move(-maxSpeed, 0);
	}

	/**
	 * Override this to hook into the move method.
	 */
	protected void onMove()
	{
	}

	private void processMove()
	{
//		clearBlockedNodes();
//		NavMap.updateNavGraph(this);

//		speed = ((speed - decelerationRate) < 0) ? 0 : speed - decelerationRate;

		Gdx.app.debug("Entity Position", toString());
	}

//	public void setBlockedNodes(List<Node> blocked)
//	{
//		blockedNodes = blocked;
//	}
//
//	public void clearBlockedNodes()
//	{
//		if (blockedNodes != null)
//		{
//			for (Node node : blockedNodes)
//			{
//				node.setPassable(true);
//			}
//		}
//	}

	public final int getX()
	{
		return (int)rect.x;
	}

	public final int getY()
	{
		return (int)rect.y;
	}

	public final int getCenterX()
	{
		return (int)(rect.x + rect.width / 2.f);
	}

	public final int getCenterY()
	{
		return (int)(rect.y + rect.height / 2.f);
	}

	protected final float getSpeed()
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

	public final void stopMoving()
	{
		speed = 0;
	}

//	public final void setSpeed(float change)
//	{
//		speed += change;
//		if (speed < 0)
//		{
//			speed = 0;
//		}
//		else if (speed > maxSpeed)
//		{
//			speed = maxSpeed;
//		}
//	}

	public final void rotateToFace(int x, int y, boolean rotateSprite)
	{
		int centerX = (int)(rect.x + rect.width / 2.f);
		int centerY = (int)(rect.y + rect.height / 2.f);
		float angle = GameMath.angleToFace(centerX, centerY, x, y) * MathUtils.radiansToDegrees;
		if (rotateSprite)
		{
			sprite.setRotation(angle + (MathUtils.PI / 2.f * MathUtils.radiansToDegrees));
			onRotateSprite(angle);
		}

		onRotate(angle);
		setMovementVector();
	}

	public final void rotateToFace(float x, float y, boolean rotateSprite)
	{
		rotateToFace((int)x, (int)y, rotateSprite);
	}

	public final void rotateToFace(float x, float y)
	{
		rotateToFace((int)x, (int)y, true);
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

	/**
	 * Provides a hook into the rotateToFace method. Override this to receive notification when
	 * the entity's sprite is rotated.
	 */
	protected void onRotateSprite(@SuppressWarnings("unused") float rotation)
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

	public final void addController(Controller controller)
	{
		controllers.add(controller);
	}

	public final void removeController(Class<? extends Controller> controllerType)
	{
		for (final Controller controller : controllers)
		{
			if (controller.getClass().equals(controllerType))
			{
				controllers.removeValue(controller, true);
			}
		}
	}

	public final void setCommand(EntityCommand c)
	{
		if (!command.isFinished())
		{
			command.cancel();
		}
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

			if (isSelected || App.debug.areBoundingBoxesAlwaysVisible())
			{
				batch.rect(rect.x, rect.y, rect.width, rect.height);

//				if (centerRect != null && App.debugEnabled())
//				{
//					batch.rect(centerRect.x, centerRect.y, centerRect.width, centerRect.height);
//				}
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

		// Process the command, if it is not finished.
		if (!command.isFinished())
		{
			command.update();
		}

		// Update all entity controllers.
		for (final Controller c : controllers)
		{
			c.update(this, world);
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
