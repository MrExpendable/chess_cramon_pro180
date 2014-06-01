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
				if(isWhiteKingInCheck())
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
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
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
	
	public boolean isWhiteKingInCheck()
	{
		//Create copies of the board at its current state so that I'm not modifying the game
		Chessboard chessboardCopy = new Chessboard(game.getBoard());
		board.Square[][] squaresCopy = chessboardCopy.getSquares();
		
		Location whiteKingLoc = getKingLocation(chessboardCopy, true);
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				//If there's a piece there
				if(squaresCopy[j][i].getPiece() != null)
				{
					//if piece and king are not same color
					if(!squaresCopy[j][i].getPiece().isWhite)
					{
						//if piece can move to king
						if(chessboardCopy.testMovePiece(new Location(j, i), whiteKingLoc))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
		
		//create a new king, get its location, and send its location to all pieces that arent its color to see if they can cap him?
	}
	
	public boolean isBlackKingInCheck()
	{
		return false;
	}
}
