package io;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileIO 
{
	InputStream inputStream;
	
	public void ReadFile(String filename)
	{
		System.out.println("Before try");
		try
		{
			System.out.println("It didn't get so far");
			inputStream = getClass().getResourceAsStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			System.out.println("It got this far");
			
			while(br.ready())
			{
				System.out.println("ready");
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			
		}
	}
}
