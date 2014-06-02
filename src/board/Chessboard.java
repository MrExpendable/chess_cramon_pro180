package board;

import pieces.Piece;

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
		//Square currentSpace = squares[initRow][initCol];
		Piece currentPiece = squares[initRow][initCol].getPiece();
		
		if(currentPiece != null)
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