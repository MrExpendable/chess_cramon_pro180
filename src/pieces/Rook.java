package pieces;

import board.Location;

public class Rook extends Piece
{
	public Rook(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "R";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		final int MAXBOUNDS = 8;
		return (fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS) && Math.abs(fromCol - toCol) > 0 
				|| (fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0;
	}
}
