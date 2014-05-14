package io;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileIO
{
	//Reads file in and calls parsing methods
	public void readFile(String filePath)
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
			
			FileParser parser = new FileParser();
			
			parser.parsePiecePlacement(parsedList);
			parser.parsePieceMovement(parsedList);
			parser.parsePieceCapture(parsedList);
			parser.parseSpecialMovement(parsedList);
		}
		
//		String line;
//        File file = new File(fileName);
//        System.out.println(file.exists());
//        
//        try(BufferedReader br = new BufferedReader(new FileReader(file));)
//        {
//        	while((line = br.readLine()) != null)
//            {
//                System.out.println(line);
//            }
//        	
//        	br.close();
//        }
		
//		try
//		{
//			FileReader in = new FileReader("C:/test.txt");
//		    BufferedReader br = new BufferedReader(in);
//		    String line;
//		    while ((line = br.readLine()) != null) 
//		    {
//		        System.out.println(line);
//		    }
//		    
//		    in.close();
//		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
