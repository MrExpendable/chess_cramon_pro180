package pieces;

import board.Chessboard;

public class Knight extends Piece
{
	public Knight(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "N";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		Piece pieceAtLocation = copyBoard.getSquares()[toRow][toCol].getPiece();
		
		//If piece at end position is of its own color, can't move there
		if(pieceAtLocation != null && pieceAtLocation.isWhite == isWhite)
		{
			return false;
		}
		else
		{
			return ((Math.abs(fromCol - toCol) == 2 && Math.abs(fromRow - toRow) == 1) ||
					(Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 2));
		}
	}
}
