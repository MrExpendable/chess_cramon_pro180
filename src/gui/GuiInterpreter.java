package gui;

import board.*;
import game.GameManager;

import java.util.ArrayList;

/*This class acts as a controller that handles input from ChessGameWindow and sends it to GameManager*/
public class GuiInterpreter 
{
	private Chessboard guiChessboard;
	private GameManager gm;
	public final int BOARD_LENGTH = 8;
	
	public GuiInterpreter(String cmdArgs)
	{
		gm = new GameManager(cmdArgs);
		guiChessboard = new Chessboard(gm.getChessboard());
		
		printBoard();
	}
	
	public Chessboard getChessboard()
	{
		return guiChessboard;
	}
	
	//makeMove
	
	public ArrayList<Location> getAvailableMoves()
	{
		ArrayList<Location> toReturn = new ArrayList<Location>();
		
		return toReturn;
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
				if(guiChessboard.getSquares()[i][j].getPiece() == null)
				{
					//If the element is null, print - instead to signify an empty space
					System.out.print("-   ");
				}
				else
				{
					//Else, check the type of the piece in the array, and print it out
					System.out.print(guiChessboard.getSquares()[i][j].toString() + "  ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
