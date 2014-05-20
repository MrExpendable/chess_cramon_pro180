package game;

import java.util.Scanner;

public class GameManager 
{
	private Game game;
	private boolean isRunning = true;
	
	public GameManager(String path)
	{
		game = new Game();
		game.startGame(path);
	}
	
	public void runGame()
	{
		while(isRunning)
		{
			System.out.println("Choose");
			Scanner scan = new Scanner(System.in);
			String userInput = scan.nextLine();
			System.out.println(userInput);
		}
	}
}
