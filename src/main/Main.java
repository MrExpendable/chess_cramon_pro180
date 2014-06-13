package main;

import gui.ChessGameWindow;

public class Main 
{	
	public static void main(String[] args)
	{
		ChessGameWindow gui;
		
		if(args != null)
		{
			gui = new ChessGameWindow(args[0]);
			gui.createWindowComponents(gui.getContentPane());
			gui.addPieces();
			gui.pack();
		}
		else
		{
			System.out.println("No filename provided; provide command line arguments");
		}
	}
	
	//old console version of main
//	public static void main(String[] args)
//	{
//		if(args != null)
//		{
//			GameManager gm = new GameManager(args[0]);
//			gm.runGame();
//		}
//		else
//		{
//			System.out.println("No filename provided; provide command line arguments");
//		}
//	}
	
	//FOR FUTURE REFERENCE:
	//Command line arguments provided through eclipse are in this format:
	// /resources/filename
	//**exclude file extensions, just provide file names
}
