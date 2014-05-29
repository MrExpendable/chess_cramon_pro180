package game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pieces.Piece;
import board.Chessboard;
import board.Location;

public class Player
{
	private boolean isWhite;
	
	public Player()
	{
	}
	
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
		System.out.println("Choose a piece");
		Scanner scan = new Scanner(System.in);
		String choosePiece = scan.nextLine();
		System.out.println("Make a move");
		String makeMove = scan.nextLine();
		
		//if input is valid
		if(isInputValid(choosePiece, makeMove))
		{
			Location init = new Location(choosePiece.toUpperCase());
			Location fin = new Location(makeMove.toUpperCase());
			Piece playerPiece = board.getSquares()[init.getRow()][init.getColumn()].getPiece();
			
			//if chosen piece isn't null
			if(playerPiece != null)
			{
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
				//piece is null
				return false;
			}
		}
		else
		{
			//valid input not provided by player
			System.out.println("You didn't provide valid input (example: A1, B3, H4, etc.)");
			return false;
		}
	}
	
	//Checks if player input was valid
	public boolean isInputValid(String choosePiece, String makeMove)
	{
		Pattern playerChoice = Pattern.compile("(?<initPosition>[a-hA-H][1-8])");
		Pattern movement = Pattern.compile("(?<finalPosition>[a-hA-H][1-8])");
		Matcher matcher = playerChoice.matcher(choosePiece);
		
		//if player doesn't provide correct input for choosing a piece
		if(!matcher.find())
		{
			return false;
		}
		
		matcher.reset();
		matcher = movement.matcher(makeMove);
		
		//if player doesn't provide correct input for making a move with the piece
		if(!matcher.find())
		{
			return false;
		}
		
		//if input is valid
		return true;
	}
}
