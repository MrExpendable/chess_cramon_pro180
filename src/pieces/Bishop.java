package pieces;

import board.Chessboard;
import board.Location;

public class Bishop extends Piece
{
	public Bishop(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "B";
	}
	
	public Bishop(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "B";
	}
	
	public boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		int dirX = (toCol > fromCol) ? 1 : -1;
	    int dirY = (toRow > fromRow) ? 1 : -1;
	    int greaterColumn = fromCol > toCol ? fromCol : toCol;
	    int lesserColumn = fromCol < toCol ? fromCol : toCol;
	    int greaterRow = fromRow > toRow ? fromRow : toRow;
	    int lesserRow = fromRow < toRow ? fromRow : toRow;
	    board.Square[][] squares = copyBoard.getSquares();
	    
	    if(fromCol != toCol && fromRow != toRow)
	    {
	    	for(int i = 1; i < (greaterColumn - lesserColumn); i++)
	    	{
	    		//works
	    		if(dirX > 0 && dirY > 0)
	    		{
	    			if(squares[lesserRow + i][lesserColumn + i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		//works
	    		else if(dirX > 0 && dirY < 0)
	    		{
	    			if(squares[greaterRow - i][lesserColumn + i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		else if(dirX < 0 && dirY > 0)
	    		{
	    			if(squares[lesserRow + i][greaterColumn - i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		else if(dirX < 0 && dirY < 0)
	    		{
	    			if(squares[greaterRow - i][greaterColumn - i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    	}
	    	
	    	return false;
	    }
	    
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
		//Otherwise, return true if there are no pieces along the way and the move is valid
		else
		{
			return (toCol != fromCol && fromRow != toRow) && ((Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) || 
							Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol));
		}
	}
}
