package pieces;

import board.Chessboard;
import board.Location;

public class Rook extends Piece
{
	public Rook(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "R";
	}
	
	public Rook(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "R";
	}
	
	//copy constructor
	public Rook(Rook r) 
	{
		isWhite = r.isWhite;
		location = r.location;
		name = r.name;
	}

	public boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		board.Square[][] squares = copyBoard.getSquares();
	    int greaterCol = (fromCol > toCol) ? fromCol : toCol;
	    int lesserCol = (fromCol < toCol) ? fromCol : toCol;
	    int greaterRow = (fromRow > toRow) ? fromRow : toRow;
	    int lesserRow = (fromRow < toRow) ? fromRow : toRow;
	    
	    
	    //new method, try this
	    if((fromCol != toCol) && (fromRow == toRow))
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
		
		//if there's a piece at the end destination and it's the same color
		if(pieceAtLocation != null && pieceAtLocation.isWhite == isWhite)
		{
			return false;
		}
		//Otherwise, return true if there are no pieces along the way and the move is valid
		else
		{
			return !(isMoveObstructed(fromCol, fromRow, toCol, toRow, copyBoard)) && 
					(fromRow == toRow && Math.abs(fromCol - toCol) <= MAXBOUNDS) && Math.abs(fromCol - toCol) > 0 
						|| (fromCol == toCol && Math.abs(fromRow - toRow) <= MAXBOUNDS) && Math.abs(fromRow - toRow) > 0;
		}
	}
}
