/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

import java.util.Queue;

import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.nav.Search;
import com.divergentthoughtsgames.rts.nav.StraightLineHeuristic;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Entity;

public class MoveCommand extends AbstractEntityCommand<Entity>
{
	private Node nextNode;
	private Queue<Node> path;
	
	
	public MoveCommand(Entity entity, float targetX, float targetY)
	{
		super(entity);
		
		Node startNode = Find.node(entity);
		Node targetNode = Find.node(targetX, targetY);
		
		path = Search.aStar(startNode, targetNode, StraightLineHeuristic.get());
		nextNode = path.poll();
		rotateToFace(entity, nextNode);
	}

	@Override
	public void update()
	{
		if (nextNode == null)
		{
			setFinished(true);
			return;
		}
		
		if (entity.getRect().contains(nextNode.getX(), nextNode.getY()))
		{
			nextNode = path.poll();
			rotateToFace(entity, nextNode);
		}
		else
		{
			// Move the entity.
			
		}
	}
	
	private static void rotateToFace(Entity entity, Node nextNode)
	{
		if (nextNode != null)
		{
			entity.rotateToFace(nextNode.getX(), nextNode.getY());
		}
	}
}
