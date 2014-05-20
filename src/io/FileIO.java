package io;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileIO
{
	//Reads file in and calls parsing methods
	public ArrayList<String> readFile(String filePath)
	{
		ArrayList<String> parsedList = new ArrayList<String>();
		try
		{
			InputStream in = getClass().getResourceAsStream(filePath);
			InputStreamReader is = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();
	
			while(read != null) 
			{
			    parsedList.add(read);
			    read = br.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return parsedList;
    }
}
