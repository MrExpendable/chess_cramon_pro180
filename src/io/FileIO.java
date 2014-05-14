package io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileIO 
{
	InputStream input;
	//BufferedReader br = null;
	
	public void TestReadFile()
	{
		String filePath = "/resources/piecePlacement.txt";
		InputStream testStream = getClass().getResourceAsStream(filePath);
		System.out.println("It got this far");
	}
	
	public void ReadFile(String filename)
	{
		try
		{
			String nextLine = "";
			input = getClass().getResourceAsStream(filename);
			
			try(BufferedReader br = new BufferedReader(new InputStreamReader(input)))
			{
				while(br.ready())
				{
					nextLine = br.readLine();
					System.out.println(nextLine);
				}
			}
			catch(IOException e) 
			{
			    e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			System.err.println("Unable to read file");
			e.printStackTrace();
		}
//		finally
//		{
//			//Close the InputStream
//			try
//			{
//				input.close();
//			}
//			catch(IOException e)
//			{
//				System.err.println("Couldn't close input stream");
//				e.printStackTrace();
//			}
//		}
	}
}
