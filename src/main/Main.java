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
		
		FileParser parser = new FileParser();
		parser.parsePiecePlacement();
	}
}
