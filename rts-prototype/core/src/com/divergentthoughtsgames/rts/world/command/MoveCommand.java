/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.nav.Search;
import com.divergentthoughtsgames.rts.nav.StraightLineHeuristic;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Entity;

public class MoveCommand extends AbstractEntityCommand<Entity>
{
	private Node previousNode;
	private Node nextNode;
	private final Vector2 finalTarget;
	private Vector2 nextTarget;
	private Queue<Node> path;
	
	public MoveCommand(Entity entity, float targetX, float targetY)
	{
		super(entity);
		
		Node startNode = Find.node(entity);
		Node targetNode = Find.node(targetX, targetY);
		finalTarget = new Vector2(targetX, targetY);
		nextTarget = new Vector2();
		
		startNode.setPassable(true);
		path = Search.aStar(startNode, targetNode, StraightLineHeuristic.get());
		
		// Get the next node, and face it.
		setNextNode();
	}

	@Override
	public void update()
	{
		if (isFinished())
		{
			return;
		}
		
//		if (entity.getNode().equals(nextNode))
		if (entity.contains((int)nextTarget.x, (int)nextTarget.y))
		{
			if (nextTarget == finalTarget)
			{
				setFinished(true);
			}
			else
			{
				setNextNode();
				Gdx.app.debug("Move Commmand", "Found next node");
			}
		}
		else
		{
			rotateToFace(entity, nextTarget, finalTarget);
			entity.move();
		}
		
		if (App.debugEnabled())
		{
			Node entityNode = entity.getNode();
			Gdx.app.debug("Entity Node", entityNode.toString());
			if (nextNode != null) Gdx.app.debug("Entity Next Node", nextNode.toString());
			App.graphics.drawPath(nextNode, path);
		}
	}
	
	@Override
	protected void onFinished()
	{
		entity.stopMoving();
		entity.setCommand(NullCommand.get());
		Gdx.app.debug("Move Command", "Move Command finished");
	}
	
	@Override
	protected void onCancelled()
	{
		entity.stopMoving();
		previousNode.setPassable(true);
		entity.setCommand(NullCommand.get());
		Gdx.app.debug("Move Command", "Move Command cancelled");
	}
	
	private void setNextNode()
	{
		if (previousNode != null)
		{
			previousNode.setPassable(true);
		}
		
		nextNode = path.poll();
		if (nextNode != null)
		{
			nextTarget.set(nextNode.getCenterX(), nextNode.getCenterY());
			rotateToFace(entity, nextTarget, finalTarget);
		}
		else
		{
			nextTarget = finalTarget;
			entity.rotateToFace(nextTarget.x, nextTarget.y);
		}

		entity.setSpeedMax();
		
		previousNode = entity.getNode();
		previousNode.setPassable(false);
	}
	
	private static void rotateToFace(Entity entity, Vector2 nextTarget, Vector2 finalTarget)
	{
		entity.rotateToFace(nextTarget.x, nextTarget.y, nextTarget != finalTarget);
	}
}
