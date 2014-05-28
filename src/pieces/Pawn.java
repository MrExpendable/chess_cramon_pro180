package pieces;

public class Pawn extends Piece
{
	public Pawn(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "P";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		//IMPLEMENT MOVEMENT LOGIC IN MODULE 4
		return true;
	}
}
