package com.divergentthoughtsgames.train.nav;

/**
 * The row, column location of a Node in the nav graph.
 * @author Christopher D. Canfield
 */
public class Location
{
	// The row number in the maze.
	private int row;
	// The column number in the maze.
	private int column;
	
	public Location(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the row.
	 * @return The row number.
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Returns the column.
	 * @return The column number.
	 */
	public int getColumn()
	{
		return column;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(row).append(",").append(column).append(")");
		return sb.toString();
	}
}
