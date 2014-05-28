package game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import board.Chessboard;
import board.Location;

public class Player
{
	private boolean isWhite;
	
	//Asks for input and then sends it to the board to move a piece
	public void takeTurn(Chessboard board)
	{
		System.out.println("Choose a piece");
		Scanner scan = new Scanner(System.in);
		String choosePiece = scan.nextLine();
		System.out.println("Make a move");
		String makeMove = scan.nextLine();
		
		if(isInputValid(choosePiece, makeMove))
		{
			Location init = new Location(choosePiece.toUpperCase());
			Location fin = new Location(makeMove.toUpperCase());
			
			board.movePiece(init, fin);
		}
		else
		{
			System.out.println("You didn't provide valid input (example: A1, B3, H4, etc.)");
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
