package board;
import pieces.Piece;

public class Square 
{
	private Piece piece;
	
	public Square()
	{
		piece = null;
	}
	
	public Square(Piece p)
	{
		piece = p;
	}
	
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	public String toString()
	{
		return piece.getName();
	}
}
