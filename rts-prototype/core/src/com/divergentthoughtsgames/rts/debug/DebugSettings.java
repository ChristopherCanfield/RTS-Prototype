package com.divergentthoughtsgames.rts.debug;


public class DebugSettings
{
	private boolean debugEnabled;

	private boolean boundingBoxesAlwaysVisible;

	private boolean navGraphVisible;

	/**
	 * Whether debugging is currently enabled. Debugging adds additional logging and visualizations.
	 * @return true if debugging is enabled.
	 */
	public boolean isEnabled()
	{
		return debugEnabled;
	}

	/**
	 * Sets whether debugging is enabled.
	 * @param val true to enable debugging, or false otherwise.
	 */
	public void setEnabled(boolean val)
	{
		debugEnabled = val;
	}

	/**
	 * Sets whether debugging is enabled. If debugging was previously enabled, it will now be disabled,
	 * while if it was previously disabled, it will now be enabled.
	 */
	public void setEnabled()
	{
		debugEnabled = !debugEnabled;
	}

	public boolean areBoundingBoxesAlwaysVisible()
	{
		return boundingBoxesAlwaysVisible;
	}

	public void setBoundingBoxesAlwaysVisible()
	{
		boundingBoxesAlwaysVisible = !boundingBoxesAlwaysVisible;
	}

	/**
	 * Returns true if the navigation graph is visible, or false otherwise.
	 * @return true if the navigation graph is visible, or false otherwise.
	 */
	public boolean isNavGraphVisible()
	{
		return navGraphVisible;
	}

	/**
	 * Sets the navigation graph to visible if it was not previously visible, or not visible if it
	 * was previously visible.
	 */
	public void setNavGraphVisible()
	{
		navGraphVisible = !navGraphVisible;
	}
}
