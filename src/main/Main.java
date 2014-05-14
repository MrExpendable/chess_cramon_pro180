package main;
import io.FileIO;

public class Main 
{
	public static void main(String[] args)
	{
		readFile(args);
	}
	
	public static void readFile(String[] args)
	{
		FileIO testIO = new FileIO();
		if(args[0].length() > 0)
		{
			testIO.readFile(args[0]);
		}
		else
		{
			System.out.println("No filename provided; provide command line arguments");
		}
	}
}
