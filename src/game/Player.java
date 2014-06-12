package game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pieces.*;
import board.Chessboard;
import board.Location;
import board.Square;

public class Player
{
	private boolean isWhite;
	
	public Player() {}
	
	public Player(boolean b)
	{
		isWhite = b;
	}
	
	public boolean isPlayerWhite()
	{
		return isWhite;
	}
	
	//Asks for input and then sends it to the board to move a piece
	public boolean takeTurn(Chessboard board)
	{
		System.out.println("Choose a piece (A2, B1, etc.):");
		Scanner scan = new Scanner(System.in);
		String choosePiece = scan.nextLine();
		
		//if piece choice is valid
		if(isChoiceValid(choosePiece))
		{
			Location init = new Location(choosePiece.toUpperCase());
			Piece playerPiece = board.getSquares()[init.getRow()][init.getColumn()].getPiece();
			Piece test = determinePieceType(board.getSquares()[init.getRow()][init.getColumn()].getPiece());
			
			//if the chosen piece isn't null
			if(playerPiece != null)
			{
				ArrayList<Location> possibleMoves = getPossibleMoves(playerPiece, board);
				//playerPiece = test;
				board.getSquares()[test.getLocation().getRow()][test.getLocation().getColumn()].setPiece(test);
				
				//loop through all possible moves for this piece and print them out
				System.out.println("Possible moves for this piece: \n");
				for(Location l : possibleMoves)
				{
					System.out.printf("%s", l.toString());
				}
				
				System.out.println("\nMake a move:");
				String makeMove = scan.nextLine();
				
				//if the move input is valid
				if(isMoveValid(makeMove))
				{
					Location fin = new Location(makeMove.toUpperCase());
					
					//if player piece matches player color
					if(playerPiece.isWhite == isPlayerWhite())
					{
						//if player piece can move to position on the board
						if(board.movePiece(init, fin))
						{
							return true;
						}
						else
						{
							//piece can't move there
							return false;
						}
					}
					else
					{
						//player tried to move piece that wasn't theirs
						System.out.println("THAT ISN'T YOUR PIECE DON'T DO THAT");
						return false;
					}
				}
				else
				{
					//valid input not provided
					System.out.println("You didn't provide valid input for a move (example: A2, B4, H5, etc.)");
					return false;
				}
			}
			else
			{
				//piece is null
				return false;
			}
		}
		else
		{
			//valid input not provided by player
			System.out.println("You didn't provide valid input for a choice (example: A1, B3, H4, etc.)");
			return false;
		}
	}
	
	//Checks if player's piece choice was valid
	public boolean isChoiceValid(String choosePiece)
	{
		Pattern playerChoice = Pattern.compile("(?<initPosition>[a-hA-H][1-8])");
		Matcher matcher = playerChoice.matcher(choosePiece);
		
		//if player doesn't provide correct input for choosing a piece
		if(!matcher.find())
		{
			return false;
		}
		
		//if input is valid
		return true;
	}
	
	//Checks if player's move choice was valid
	public boolean isMoveValid(String makeMove)
	{
		Pattern movement = Pattern.compile("(?<finalPosition>[a-hA-H][1-8])");
		Matcher matcher = movement.matcher(makeMove);
		
		//if player doesn't provide correct input for making a move with the piece
		if(!matcher.find())
		{
			return false;
		}
		
		return true;
	}
	
	//Gets all available moves for a given piece
	public ArrayList<Location> getPossibleMoves(Piece p, Chessboard copyBoard)
	{
		final int BOARD_LENGTH = 8;
		Chessboard copyOfBoard = new Chessboard(copyBoard);
		ArrayList<Location> toReturn = new ArrayList<Location>();
		Piece oldPiece = p;
		oldPiece = determinePieceType(p);
		
		if(p != null)
		{			
			for(int i = 0; i < BOARD_LENGTH; i++)
			{
				for(int j = 0; j < BOARD_LENGTH; j++)
				{
					Piece pieceBeforeMod = p;
					pieceBeforeMod = determinePieceType(p);
					
					int pieceCol = p.getLocation().getColumn();
					int pieceRow = p.getLocation().getRow();
					
					if(!p.isMoveObstructed(pieceCol, pieceRow, i, j, copyOfBoard))
					{
						if(p.isValidMove(pieceCol, pieceRow, i, j, copyOfBoard))
						{
							Location possibleMove = new Location(j, i);
							toReturn.add(possibleMove);
							p = pieceBeforeMod;
							//squares[pieceRow][pieceCol].setPiece(pieceBeforeMod);
						}
					}
				}
			}
		}
		
		p = oldPiece;
		return toReturn;
	}
	
	public Piece determinePieceType(Piece p)
	{
		if(p instanceof Pawn)
		{
			Pawn toReturn = (Pawn)p;
			return new Pawn(toReturn);
		}
		else if(p instanceof Knight)
		{
			Knight toReturn = (Knight)p;
			return new Knight(toReturn);
		}
		else if(p instanceof Queen)
		{
			Queen toReturn = (Queen)p;
			return new Queen(toReturn);
		}
		else if(p instanceof Rook)
		{
			Rook toReturn = (Rook)p;
			return new Rook(toReturn);
		}
		else if(p instanceof Bishop)
		{
			Bishop toReturn = (Bishop)p;
			return new Bishop(toReturn);
		}
		else if(p instanceof King)
		{
			King toReturn = (King)p;
			return new King(toReturn);
		}
		else
		{
			//shouldn't reach this, only adding this to satisfy the java gods
			return null;
		}
	}
}
