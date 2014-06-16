/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.graphics.effect;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Interface for graphical effects.
 * @author Christopher D. Canfield
 */
public interface Effect
{
	void render(ShapeRenderer renderer);
	boolean isFinished();
}
