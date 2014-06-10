package pieces;

import board.Chessboard;
import board.Location;

public class Knight extends Piece
{
	public Knight(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "N";
	}
	
	public Knight(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "N";
	}
	
	//find some way to not use this, instead just make it so that knight ignores collision
	public boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		return false;
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
