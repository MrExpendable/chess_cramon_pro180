package board;

import pieces.*;

public class Chessboard 
{
	private Square[][] squares;
	private static final int BOARD_LENGTH = 8;
	
	//Instantiate the board
	public Chessboard()
	{
		squares = new Square[BOARD_LENGTH][BOARD_LENGTH];
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				if(squares[i][j] == null)
				{
					squares[i][j] = new Square();
				}
			}
		}
	}
	
	//Used for creating a copy of the chessboard so as not to modify the game
	public Chessboard(Chessboard toCopy)
	{
		squares = new Square[BOARD_LENGTH][BOARD_LENGTH];
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				squares[i][j] = toCopy.squares[i][j];
			}
		}
	}
	
	//Return the board
	public Square[][] getSquares()
	{
		return squares;
	}
	
	//Moves a piece on the board, returns true if successful, false if not
	public boolean movePiece(Location init, Location fin)
	{
		int initCol = init.getColumn();
		int initRow = init.getRow();
		int finCol = fin.getColumn();
		int finRow = fin.getRow();
		Piece currentPiece = squares[initRow][initCol].getPiece();
		
		//if there's a piece in the current space
		if(currentPiece != null)
		{
			//if it isn't obstructed by any other pieces along its path
			if(!isPieceObstructed(init, fin, squares, currentPiece))
			{
				if(currentPiece.isValidMove(initCol, initRow, finCol, finRow, this))
				{
					squares[finRow][finCol].setPiece(currentPiece);
					squares[initRow][initCol].setPiece(null);
					return true;
				}
				else
				{
					System.out.println("Piece can't move there");
					return false;
				}
			}
			else
			{
				System.out.println("There's a piece in the way");
				return false;
			}
		}
		else
		{
			System.out.println("No piece in that spot to move");
			return false;
		}
	}
	
	//Detects check
	public boolean detectCheck(Location init, Location fin)
	{
		int initCol = init.getColumn();
		int initRow = init.getRow();
		int finCol = fin.getColumn();
		int finRow = fin.getRow();
		
		Piece currentPiece = squares[initRow][initCol].getPiece();
		
		return (currentPiece != null) && (currentPiece.isValidMove(initCol, initRow, finCol, finRow, this));
	}
	
	//Checks if a piece is obstructed along its move path
	public boolean isPieceObstructed(Location init, Location fin, Square[][] obstructedBoard, Piece pieceToCheck)
	{
		//I'm thinking that I should take the locations, subtract the columns and rows from final and initial positions, then iterate over the differences
		//Once I'm iterating over the differences, I check if there's a piece in a specific space
		//If there's a piece, return true, but if it's empty, return false
		
		Location greaterLoc, lesserLoc;
		
		if((init.getColumn() + init.getRow()) > (fin.getColumn() + fin.getRow()))
		{
			greaterLoc = init;
			lesserLoc = fin;
		}
		else
		{
			greaterLoc = fin;
			lesserLoc = init;
		}
		
		//doesn't break, but dark bishop and dark queen can move somewhere they shouldn't be allowed to move on their first moves
		//it only happens when trying to move up and right
		int colDif = greaterLoc.getColumn() - lesserLoc.getColumn();
		int rowDif = greaterLoc.getRow() - lesserLoc.getRow();
		boolean isObstructed = false;
		
		//if the piece isn't a knight
		if(!(pieceToCheck instanceof Knight))
		{
			//for cardinal movement(up/down/left/right)
			if(colDif > 0)
			{
				for(int i = 1; i < colDif; i++)
				{
					if(obstructedBoard[greaterLoc.getRow()][greaterLoc.getColumn() - i].getPiece() != null
							&& obstructedBoard[greaterLoc.getRow()][greaterLoc.getColumn() - i].getPiece() != pieceToCheck)
					{
						isObstructed = true;
					}
				}
			}
			else if(rowDif > 0)
			{
				for(int i = 1; i < rowDif; i++)
				{
					if((obstructedBoard[greaterLoc.getRow() - i][greaterLoc.getColumn()].getPiece() != null)
							&& (obstructedBoard[greaterLoc.getRow() - i][greaterLoc.getColumn()].getPiece() != pieceToCheck))
					{
						isObstructed = true;
					}
				}
			}
			//for ordinal movement(diagonal)
			else if(colDif > 0 && rowDif > 0)
			{
				for(int i = 1; i < colDif; i++)
				{
					if((obstructedBoard[greaterLoc.getRow() - i][greaterLoc.getColumn() - i].getPiece() != null)
							&& (obstructedBoard[greaterLoc.getRow() - i][greaterLoc.getColumn() - i].getPiece() != pieceToCheck))
					{
						isObstructed = true;
					}
				}
			}
		}
		
		return isObstructed;
	}
	
	//Print the board
	public void printBoard()
	{
		System.out.println("\n       a   b   c   d   e   f   g   h\n");
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			//Prints out the numbers on the side of the board
			System.out.print(i + 1 + "      ");
			
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				if(squares[i][j].getPiece() == null)
				{
					//If the element is null, print - instead to signify an empty space
					System.out.print("-   ");
				}
				else
				{
					//Else, check the type of the piece in the array, and print it out
					System.out.print(squares[i][j].toString() + "  ");
				}
			}
			System.out.println("");
		}
	}
	
	
	
	/*
	 * Old code that I'm keeping for possible later testing
	 */
	
//	System.out.println("Column: " + col);
//	System.out.println("Row: " + row);
//	System.out.println(isPieceWhite);
//	System.out.println(pieceType);
	
	
	//Testing
//	System.out.println(squares[0][0].getPosition().toString());
//	System.out.println("Checking if positions are equal...");
//	if(squares[0][0].getPosition().toString().equals(positionToFill))
//	{
//		System.out.println("SUCCESS");
//	}
//	else
//	{
//		System.out.println("FAILURE");
//	}
}