package game;

import java.util.ArrayList;

import io.FileIO;
import io.FileParser;
import board.Chessboard;
import board.Square;

public class Game
{
	private Chessboard board;
	
	public Game()
	{
		board = new Chessboard();
	}
	
	//Starts IO, instantiates objects in this scope because I won't be using them outside of this method
	public void startGame(String path)
	{
		FileIO io = new FileIO();
		FileParser parser = new FileParser();
		
		ArrayList<String> piecePlacements = io.readFile(path);
		parser.parsePiecePlacement(piecePlacements, board);
		
		board.printBoard();
	}
	
	public Chessboard getBoard()
	{
		return board;
	}
}
