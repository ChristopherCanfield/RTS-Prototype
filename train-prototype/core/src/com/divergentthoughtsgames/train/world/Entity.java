package com.divergentthoughtsgames.train.world;

import java.util.UUID;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public abstract class Entity
{
	private UUID id;
	
	private ArrayMap<Class<? extends Component>, Component> components;
	
	private Array<Controller> controllers;
	
	private boolean disposed;
	
	protected Entity()
	{
		this.components = new ArrayMap<>(false, 4);
		this.controllers = new Array<>(false, 1);
	}
	
	public UUID getId()
	{
		return id;
	}
	
	protected void addComponent(Component component)
	{
		components.put(component.getClass(), component);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T get(Class<? extends Component> componentType)
	{
		return (T)components.get(componentType);
	}
	
	protected void addController(Controller controller)
	{
		controllers.add(controller);
	}
	
	public void update()
	{
		throw new UnsupportedOperationException();
	}
	
	public void dispose()
	{
		disposed = true;
		onDispose();
	}
	
	protected void onDispose()
	{
	}
	
	public boolean isDisposed()
	{
		return disposed;
	}
}
