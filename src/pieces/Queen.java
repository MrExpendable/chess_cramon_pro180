package pieces;

import board.Chessboard;

public class Queen extends Piece
{
	public Queen(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "Q";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		final int MAXBOUNDS = 8;
		return ((fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS && Math.abs(fromCol - toCol) > 0) || 
				(fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0 ||
				(toCol != fromCol && fromRow != toRow && (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol))));
	}
}
