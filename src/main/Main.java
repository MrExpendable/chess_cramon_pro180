package main;
import io.FileIO;

public class Main 
{
	public static void main(String[] args)
	{
		FileIO fileIO = new FileIO();
		
		if(args != null)
		{
			fileIO.readFile(args[0]);
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
