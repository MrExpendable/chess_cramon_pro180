package io;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileIO
{
	//Reads file in and calls parsing methods
	public void readFile(String fileName)
	{
		String line;
        File file = new File(fileName);
        
        try(BufferedReader br = new BufferedReader(new FileReader(file));)
        {
        	while((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        	
        	br.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
}
