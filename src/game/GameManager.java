package game;

public class GameManager 
{
	private Game game;
	private Player player1, player2;
	private boolean isPlayer1Turn = true;
	private boolean isRunning = true;
	
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
	
	public boolean isWhiteKingInCheck()
	{
		return false;
	}
	
	public boolean isBlackKingInCheck()
	{
		return false;
	}
}
