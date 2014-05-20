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
}