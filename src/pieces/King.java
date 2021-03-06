package pieces;

import board.Chessboard;
import board.Location;

public class King extends Piece
{
	public King(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "K";
	}
	
	public King(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "K";
	}
	
	//copy constructor
	public King(King k) 
	{
		isWhite = k.isWhite;
		location = k.location;
		name = k.name;
	}

	//find some way to not use this, same as knight
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
			//check for collision
			return ((fromRow == toRow && Math.abs(fromCol - toCol) == 1) || (fromCol == toCol && Math.abs(fromRow - toRow) == 1) || 
					(toCol != fromCol && fromRow != toRow && ((Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1))));
		}
	}
}
