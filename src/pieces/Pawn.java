package pieces;

import board.Chessboard;
import board.Location;

public class Pawn extends Piece
{
	private boolean hasMoved = false;
	
	public Pawn(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "P";
	}
	
	public Pawn(boolean isPieceWhite, Location l)
	{
		isWhite = isPieceWhite;
		location = l;
		name = "P";
	}
	
	public boolean isMoveObstructed(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		board.Square[][] squares = copyBoard.getSquares();
		//if hasn't moved and can move 2 spaces, check for obstruction depending on whether it's a white piece or not
		if(!hasMoved)
		{
			if(isWhite)
			{
				for(int i = 1; i < (toRow - fromRow); i++)
				{
					if(squares[fromRow + i][fromCol].getPiece() != null)
					{
						return true;
					}
				}
				
				return false;
			}
			else
			{
				for(int i = 1; i < (fromRow - toRow); i++)
				{
					if(squares[fromRow - i][fromCol].getPiece() != null)
					{
						return true;
					}
				}
				
				return false;
			}
		}
		//otherwise, just return false cause it can only move one space anyways
		else
		{
			return false;
		}
	}
	
	//Checks if pawn's first move is valid
	public boolean isValidFirstMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		
		//if piece is white
		if(isWhite)
		{
			//if move is valid
			if((fromCol == toCol && Math.abs(toRow - fromRow) <= 2 && (toRow - fromRow) > 0))
			{
				hasMoved = !hasMoved;
				return true;
			}
			else
			{
				return false;
			}
		}
		//if piece is black
		else
		{
			//if move is valid
			if((fromCol == toCol && Math.abs(toRow - fromRow) <= 2 && (fromRow - toRow) > 0))
			{
				hasMoved = !hasMoved;
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	//Checks if pawn's move is valid
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Chessboard copyBoard)
	{
		Piece pieceToCapture = copyBoard.getSquares()[toRow][toCol].getPiece();
		
		//if pawn hasn't moved
		if(!hasMoved)
		{
			//if is valid first move
			if(isValidFirstMove(fromCol, fromRow, toCol, toRow))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		//otherwise, just return whether the move is valid or not
		else
		{
			//if piece is white
			if(isWhite)
			{
				//if valid regular move
				if((fromCol == toCol && (toRow - fromRow) == 1))
				{
					//if no piece in the way
					if(pieceToCapture == null)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				//if position to move to has a piece to capture and it isn't a piece of its own color
				else if(pieceToCapture != null && pieceToCapture.isWhite != isWhite)
				{
					//if pawn can capture piece
					if(Math.abs(fromCol - toCol) == 1 && (toRow - fromRow) == 1)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			//if piece is black
			else
			{
				//if regular move is valid
				if((fromCol == toCol && (fromRow - toRow) == 1))
				{
					//if no piece in the way
					if(pieceToCapture == null)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				//else if there's a piece in the final position and it isn't of its own color
				else if(pieceToCapture != null && pieceToCapture.isWhite != isWhite)
				{
					if(Math.abs(fromCol - toCol) == 1 && (fromRow - toRow) == 1)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
		}
	}
}
