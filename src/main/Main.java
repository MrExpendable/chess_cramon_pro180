package main;
import io.FileIO;

public class Main 
{
	public static void main(String[] args)
	{
		FileIO beginIO = new FileIO();
		if(args[0] != null)
		{
			beginIO.ReadFile(args[0]);
		}
		else
		{
			System.out.println("No filename provided");
			//beginIO.ReadFile("piecePlacement");
		}
	}
	
}
