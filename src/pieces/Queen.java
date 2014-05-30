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
		Piece pieceAtLocation = copyBoard.getSquares()[toRow][toCol].getPiece();
		
		//If piece at end position is of its own color, can't move there
		if(pieceAtLocation != null && pieceAtLocation.isWhite == isWhite)
		{
			return false;
		}
		else
		{
			return ((fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS && Math.abs(fromCol - toCol) > 0) || 
					(fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0 ||
					(toCol != fromCol && fromRow != toRow && (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol))));
		}
	}
}
