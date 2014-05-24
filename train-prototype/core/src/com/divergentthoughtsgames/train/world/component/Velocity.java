package com.divergentthoughtsgames.train.world.component;

import com.divergentthoughtsgames.train.world.Component;

public class Velocity implements Component
{
	public float maxSpeed;
	
	public float accelerationRate;
	
	private float speed;
	private int direction;
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(float change)
	{
		this.speed += change;
		if (this.speed < 0)
		{
			this.speed = 0;
		}
		else if (this.speed > maxSpeed)
		{
			this.speed = maxSpeed;
		}
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int newDirection)
	{
		if (newDirection != 0)
		{
			direction = newDirection / Math.abs(newDirection);
		}
		else
		{
			direction = 0;
		}
	}
}
