package main;
import java.util.ArrayList;

import io.FileIO;
import io.FileParser;

public class Main 
{
	public static void main(String[] args)
	{
		//readFile(args);
		testParseText();
	}
	
	//Test method, don't keep this in the final release
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
	
	//Test method, don't keep this in the final release
	public static void testParseText()
	{
		FileParser testParser = new FileParser();
		
		ArrayList<String> testPlacements = new ArrayList<String>();
		testPlacements.add("Rla1");
		testPlacements.add("Nlb1");
		testPlacements.add("Blc1");
		testPlacements.add("Qld1");
		testPlacements.add("Kle1");
		testPlacements.add("Blf1");
		testPlacements.add("Nlg1");
		testPlacements.add("Rlh1");
		
		ArrayList<String> testMovements = new ArrayList<String>();
		testMovements.add("a1 a8");
		testMovements.add("b1 c3");
		testMovements.add("d1 d4");
		testMovements.add("h1 a1");
		testMovements.add("e6 d7");
		
		ArrayList<String> testCaptures = new ArrayList<String>();
		testCaptures.add("a1 a8*");
		testCaptures.add("b1 c3*");
		testCaptures.add("d1 d4*");
		testCaptures.add("h1 a1*");
		testCaptures.add("e6 d7*");
		
		testParser.parsePiecePlacement(testPlacements);
		testParser.parsePieceMovement(testMovements);
		testParser.parsePieceCapture(testCaptures);
		testParser.parseSpecialMovement("e1 g1 h1 f1");
		
//		testParser.testParsePieceMovement();
//		testParser.testParsePiecePlacement();
//		testParser.testParsePieceCapture();
//		testParser.testParseSpecialMovement();
	}
}
