package main;
import io.FileIO;
import io.FileParser;

public class Main 
{
	public static void main(String[] args)
	{
//		FileIO beginIO = new FileIO();
//		if(args[0].length() > 0)
//		{
//			beginIO.ReadFile(args[0]);
//		}
//		else
//		{
//			System.out.println("No filename provided; provide command line arguments");
//		}
//		beginIO.TestReadFile();
		
		//Only calling from main for testing purposes, I shouldn't be calling from main in the final release
		FileParser parser = new FileParser();
		parser.parsePiecePlacement();
		parser.parsePieceMovement();
		parser.parseSpecialMovement();
	}
}
