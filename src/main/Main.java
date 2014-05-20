package main;
import game.GameManager;

public class Main 
{	
	public static void main(String[] args)
	{
		if(args != null)
		{
			GameManager gm = new GameManager(args[0]);
			gm.runGame();
		}
		else
		{
			System.out.println("No filename provided; provide command line arguments");
		}
	}
	
	//FOR FUTURE REFERENCE:
	//Command line arguments provided through eclipse are in this format:
	// /resources/filename
	//**exclude file extensions, just provide file names
}
