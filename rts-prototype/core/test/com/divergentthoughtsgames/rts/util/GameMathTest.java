/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameMathTest
{

	@Test
	public void testAngleToFace()
	{
		assertEquals(Math.PI, GameMath.angleToFace(10, 10, 20, 10), 0.01f);
		assertEquals(Math.PI / 4.f, GameMath.angleToFace(20, 20, 10, 10), 0.01f);
	}

	@Test
	public void testBetween()
	{
		assertTrue(GameMath.between(5, 4, 10));
		assertTrue(GameMath.between(2, 1, 2));
		assertFalse(GameMath.between(8, 9, 10));
	}
}
