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
	
	//Return the board
	public Square[][] getSquares()
	{
		return squares;
	}
	
	//Fills the board
	public void fillBoard(String positionToFill, boolean isPieceWhite, String pieceType)
	{
		//Convert column/row to int, fill positions based on these two ints
		int col = Character.getNumericValue(positionToFill.charAt(0)) - 10;
		int row = Character.getNumericValue(positionToFill.charAt(1)) - 1;
		
		//Bishop
		if(pieceType.contains("B"))
		{
			squares[row][col].setPiece(new Bishop(isPieceWhite));
		}
		//King
		else if(pieceType.contains("K"))
		{
			squares[row][col].setPiece(new King(isPieceWhite));
		}
		//Knight
		else if(pieceType.contains("N"))
		{
			squares[row][col].setPiece(new Knight(isPieceWhite));
		}
		//Queen
		else if(pieceType.contains("Q"))
		{
			squares[row][col].setPiece(new Queen(isPieceWhite));
		}
		//Rook
		else if(pieceType.contains("R"))
		{
			squares[row][col].setPiece(new Rook(isPieceWhite));
		}
		//Pawn
		else if(pieceType.contains("P"))
		{
			squares[row][col].setPiece(new Pawn(isPieceWhite));
		}
		
		printBoard();
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