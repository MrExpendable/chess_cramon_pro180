package pieces;

public class King extends Piece
{
	public King(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "K";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		return ((fromRow == toRow && Math.abs(fromCol - toCol) == 1) || (fromCol == toCol && Math.abs(fromRow - toRow) == 1) || 
				(toCol != fromCol && fromRow != toRow && ((Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1))));
	}
}
