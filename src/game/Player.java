package game;

import java.util.Scanner;
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
		
		Location init = new Location(choosePiece.toUpperCase());
		Location fin = new Location(makeMove.toUpperCase());
		board.movePiece(init, fin);
	}
}
