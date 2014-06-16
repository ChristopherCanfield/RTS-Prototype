/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

public interface EntityCommand
{
	void update();
	void cancel();
	boolean isFinished();
}
