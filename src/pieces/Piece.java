package pieces;

public abstract class Piece 
{
	public boolean isWhite;
	public String name;
	
	public String getName()
	{
		if(isWhite)
		{
			return "l" + name;
		}
		else
		{
			return "d" + name;
		}
	}

	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow) 
	{
		return false;
	}
}