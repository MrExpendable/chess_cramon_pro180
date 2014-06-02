package pieces;

import board.Chessboard;

public class Rook extends Piece
{
	public Rook(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "R";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		final int MAXBOUNDS = 8;
		Piece pieceAtLocation = copyBoard.getSquares()[toRow][toCol].getPiece();
		
		//if there's a piece at the end destination and it's the same color
		if(pieceAtLocation != null && pieceAtLocation.isWhite == isWhite)
		{
			return false;
		}
		else
		{
			return (fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS) && Math.abs(fromCol - toCol) > 0 
					|| (fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0;
		}
	}
}
