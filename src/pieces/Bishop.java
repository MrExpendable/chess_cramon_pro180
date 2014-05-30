package pieces;

import board.Chessboard;

public class Bishop extends Piece
{
	public Bishop(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "B";
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
			return (toCol != fromCol && fromRow != toRow && ((Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) || 
					Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol)));
		}
	}
}
