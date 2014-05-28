package pieces;

public class Queen extends Piece
{
	public Queen(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "Q";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		final int MAXBOUNDS = 8;
		return ((fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS) || (fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) ||
				(toCol != fromCol && fromRow != toRow && (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol))));
	}
}
