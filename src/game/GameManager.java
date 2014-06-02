package game;

import board.Chessboard;
import board.Location;

public class GameManager 
{
	private Game game;
	private Player player1, player2;
	private boolean isPlayer1Turn = true;
	private boolean isRunning = true;
	public static final int BOARD_LENGTH = 8;
	
	public GameManager(String path)
	{
		game = new Game();
		player1 = new Player(true);
		player2 = new Player(false);
		game.startGame(path);
	}
	
	public void runGame()
	{
		//Include forfeit method, like making the king kill itself?
		while(isRunning)
		{
			//if player 1's turn
			if(isPlayer1Turn)
			{
				//Check for check
				if(isKingInCheck(true))
				{
					System.out.println("Player 1, your king is in check.");
				}
				System.out.println("Player 1's turn");
				//if player 1's turn is successful, change player turn
				if(player1.takeTurn(game.getBoard()))
				{
					isPlayer1Turn = !isPlayer1Turn;
				}
			}
			else
			{
				if(isKingInCheck(false))
				{
					System.out.println("Player 2, your king is in check.");
				}
				System.out.println("Player 2's turn");
				if(player2.takeTurn(game.getBoard()))
				{
					isPlayer1Turn = !isPlayer1Turn;
				}
			}
			
			game.getBoard().printBoard();
		}
	}
	
	//Returns a king's location
	public Location getKingLocation(Chessboard copyOfBoard, boolean isPieceWhite)
	{
		Location toReturn = null;
		board.Square[][] board = copyOfBoard.getSquares();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				//If the space has a piece
				if(board[j][i].getPiece() != null)
				{
					//if piece is a king and is the color specified by boolean that is passed in
					if(board[j][i].getPiece() instanceof pieces.King && board[j][i].getPiece().isWhite == isPieceWhite)
					{
						//Get the king and set the variable to be returned to the king's location
						toReturn = new Location(j, i);
					}
				}
			}
		}
		
		return toReturn;
	}
	
	public boolean isKingInCheck(boolean isKingWhite)
	{
		//Create copies of the board at its current state so that I'm not modifying the game
		Chessboard chessboardCopy = new Chessboard(game.getBoard());
		board.Square[][] squaresCopy = chessboardCopy.getSquares();
		
		Location kingLoc = getKingLocation(chessboardCopy, isKingWhite);
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				//If there's a piece there and piece is not same color as king
				if(squaresCopy[j][i].getPiece() != null && squaresCopy[j][i].getPiece().isWhite != isKingWhite)
				{
					//if piece can move to king
					if(chessboardCopy.detectCheck(new Location(j, i), kingLoc))
					{
						return true;
					}
				}
			}
		}
		//return false if king isn't found to be in check
		return false;
	}
}
