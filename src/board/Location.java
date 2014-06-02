package board;

public class Location 
{
	private int col, row;
	
	public Location(String pos)
	{
		//Convert column/row to int, fill positions based on these two ints
		//Getting numeric value of a-h results in 10-18, and I subtract 1 from row so that it's zero based
		col = Character.getNumericValue(pos.charAt(0)) - 10;
		row = Character.getNumericValue(pos.charAt(1)) - 1;
	}
	
	public Location(int row, int col) 
	{
		this.row = row;
		this.col = col;
	}

	public int getColumn()
	{
		return col;
	}
	
	public int getRow()
	{
		return row;
	}
}
