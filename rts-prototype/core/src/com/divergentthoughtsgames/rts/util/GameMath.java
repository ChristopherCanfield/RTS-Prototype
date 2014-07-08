/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.util;

import com.badlogic.gdx.math.MathUtils;

public abstract class GameMath
{
	/**
	 * Provides the angle needed to face the specified point, in radians.
	 * @param point1x
	 * @param point1y
	 * @param point2x
	 * @param point2y
	 * @return
	 */
	public static float angleToFace(int point1x, int point1y, int point2x, int point2y)
	{
		int deltaY = point1y - point2y;
		int deltaX = point1x - point2x;
		return MathUtils.atan2(deltaY, deltaX);
	}
	
	/**
	 * Returns true if num is between min and max, inclusive.
	 * @param num
	 * @param min
	 * @param max
	 * @return true if num is between min and max, inclusive.
	 */
	public static boolean between(float num, float min, float max)
	{
		return (num >= min && num <= max);
	}
}
