package com.divergentthoughtsgames.rts.world.controller;

import static com.badlogic.gdx.Gdx.input;

import com.badlogic.gdx.Input.Keys;
import com.divergentthoughtsgames.rts.world.Controller;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Controls an entity using the keyboard.
 * @author Christopher D. Canfield
 */
public class KeyboardEntityMoveController implements Controller
{
	@Override
	public void update(Entity entity, World world)
	{
		if (input.isKeyPressed(Keys.A))
		{
			entity.moveWest();
		}
		else if (input.isKeyPressed(Keys.D))
		{
			entity.moveEast();
		}
		else if (input.isKeyPressed(Keys.W))
		{
			entity.moveNorth();
		}
		else if (input.isKeyPressed(Keys.S))
		{
			entity.moveSouth();
		}
	}
}
