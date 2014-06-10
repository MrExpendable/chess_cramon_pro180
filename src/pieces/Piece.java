package pieces;

import board.Chessboard;
import board.Location;

public abstract class Piece 
{
	public boolean isWhite;
	public String name;
	public Location location;
	
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
	
	public void setLocation(Location l)
	{
		location = l;
	}
	
	public Location getLocation()
	{
		return location;
	}

	public abstract boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard);
	public abstract boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard);
}