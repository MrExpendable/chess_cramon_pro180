package game;

public class GameManager 
{
	private Game game;
	private Player player1;
	private boolean isRunning = true;
	
	public GameManager(String path)
	{
		game = new Game();
		player1 = new Player();
		game.startGame(path);
	}
	
	public void runGame()
	{
		while(isRunning)
		{
			player1.takeTurn(game.getBoard());
			game.getBoard().printBoard();
		}
	}
}
