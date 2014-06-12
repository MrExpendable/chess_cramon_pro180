package pieces;

import board.Chessboard;
import board.Location;

public class Queen extends Piece
{
	public Queen(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "Q";
	}
	
	public Queen(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "Q";
	}
	
	//copy constructor
	public Queen(Queen q) 
	{
		isWhite = q.isWhite;
		location = q.location;
		name = q.name;
	}

	public boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		int dirX = (toCol > fromCol) ? 1 : -1;
	    int dirY = (toRow > fromRow) ? 1 : -1;
	    int greaterCol = fromCol > toCol ? fromCol : toCol;
	    int lesserCol = fromCol < toCol ? fromCol : toCol;
	    int greaterRow = fromRow > toRow ? fromRow : toRow;
	    int lesserRow = fromRow < toRow ? fromRow : toRow;
	    board.Square[][] squares = copyBoard.getSquares();
	    
	    //Diagonal movement
		if(fromCol != toCol && fromRow != toRow)
	    {
	    	for(int i = 1; i < (greaterCol - lesserCol); i++)
	    	{
	    		if(dirX > 0 && dirY > 0)
	    		{
	    			if(squares[lesserRow + i][lesserCol + i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		else if(dirX > 0 && dirY < 0)
	    		{
	    			if(squares[greaterRow - i][lesserCol + i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		else if(dirX < 0 && dirY > 0)
	    		{
	    			if(squares[lesserRow + i][greaterCol - i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    		else if(dirX < 0 && dirY < 0)
	    		{
	    			if(squares[greaterRow - i][greaterCol - i].getPiece() != null)
		    		{
		    			return true;
		    		}
	    		}
	    	}
	    	
	    	return false;
	    }
		//Horizontal/vertical movement
		else if((fromCol != toCol) && (fromRow == toRow))
	    {
	    	for(int i = 1; i < (greaterCol - lesserCol); i++)
	    	{
	    		if(squares[greaterRow][lesserCol + i].getPiece() != null)
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    else if((fromRow != toRow) && (fromCol == toCol))
	    {
	    	for(int i = 1; i < (greaterRow - lesserRow); i++)
	    	{
	    		if(squares[lesserRow + i][greaterCol].getPiece() != null)
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }
		
		return false;
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
		//Otherwise, return true if there are no pieces along the way and the move is valid
		else
		{
			return !(isMoveObstructed(fromCol, fromRow, toCol, toRow, copyBoard)) &&
						((fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS && Math.abs(fromCol - toCol) > 0) || 
									(fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0 ||
										(toCol != fromCol && fromRow != toRow && (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol))));
		}
	}
}
