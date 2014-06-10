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

public class Move extends AbstractEntityCommand<Entity>
{
	private Node nextNode;
	private Queue<Node> path;
	
	
	public Move(Entity entity, int targetX, int targetY)
	{
		super(entity);
		
		Node startNode = Find.node(entity);
		Node targetNode = Find.node(targetX, targetY);
		
		path = Search.aStar(startNode, targetNode, StraightLineHeuristic.getInstance());
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
	}
	
	private static void rotateToFace(Entity entity, Node nextNode)
	{
		if (nextNode != null)
		{
			entity.rotateToFace(nextNode.getX(), nextNode.getY());
		}
	}
}
