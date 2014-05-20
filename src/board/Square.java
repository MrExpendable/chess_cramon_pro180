package board;
import pieces.Piece;

public class Square 
{
	private BoardPosition position;
	private Piece piece;
	
	//Probably don't need to overload the constructor this many times, figure something out that isn't so verbose?
	public Square()
	{
		position = null;
		piece = null;
	}
	
	public Square(Piece p, BoardPosition bp)
	{
		piece = p;
		position = bp;
	}
	
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	public BoardPosition getPosition()
	{
		return position;
	}
	
	public void setPosition(BoardPosition bp)
	{
		position = bp;
	}
	
	public String toString()
	{
		return piece.getName();
	}
}
