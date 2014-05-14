package io;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileParser 
{
	//Parses placement
	public void parsePiecePlacement(ArrayList<String> placements)
	{
		Pattern placement = Pattern.compile("(?<piece>[BKNPQR][dl])(?<position>[a-hA-H][1-8])");
		System.out.println("Searching for piece placements...");
		for(String s : placements)
		{
			Matcher matcher = placement.matcher(s);
			while(matcher.find())
			{
				String parsedPiece = matcher.group("piece");
				String parsedPosition = matcher.group("position");
				
				System.out.printf("%s to be placed at %s%n", parsedPiece, parsedPosition);
			}
		}
	}
	
	//Parses movement
	public void parsePieceMovement(ArrayList<String> movements)
	{
		Pattern movement = Pattern.compile("(?<initPosition>[a-hA-H][1-8]) (?<finalPosition>[a-hA-H][1-8])");
		System.out.println("Searching for piece movements...");
		for(String s : movements)
		{
			Matcher matcher = movement.matcher(s);
			while(matcher.find())
			{
				String parsedInitPosition = matcher.group("initPosition");
				String parsedFinalPosition = matcher.group("finalPosition");
				
				System.out.printf("Piece on %s moved to %s%n", parsedInitPosition, parsedFinalPosition);
			}
		}
	}
	
	//Checks for piece capturing
	public void parsePieceCapture(ArrayList<String> captures)
	{
		Pattern capture = Pattern.compile("(?<initPos>[a-h][1-8])\\s(?<finalPos>[a-h][1-8])\\*");
		System.out.println("Searching for piece captures...");
		for(String s : captures)
		{
			Matcher matcher = capture.matcher(s);
			
			if(matcher.find())
			{
				System.out.printf("Moved piece from %s to %s and captured piece on %s%n", matcher.group("initPos"), matcher.group("finalPos"), matcher.group("finalPos"));
			}
		}
	}
	
	//Parses castling
	public void parseSpecialMovement(ArrayList<String> castles)
	{
		Pattern castle = Pattern.compile("([a-h][18])\\s([a-h][18])\\s([a-h][18])\\s([a-h][18])");
		System.out.println("Searching for castling movement...");
		for(String s : castles)
		{
			Matcher matcher = castle.matcher(s);
			if(matcher.find())
			{
				System.out.printf("Moved king and rook from positions %s and %s to positions %s and %s, respectively%n", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
			}
		}
		System.out.println("");
	}
	
	
	//////TEST METHOD: Parses placement//////
	public void testParsePiecePlacement()
	{
		//Change matcher to parse text file by end of module 1
		Pattern placement = Pattern.compile("(?<piece>[BKNPQR][dl])(?<position>[a-hA-H][1-8])");
		Matcher matcher = placement.matcher("Rla1");
		System.out.println("Matching...");
		while(matcher.find())
		{
			String parsedPiece = matcher.group("piece");
			String parsedPosition = matcher.group("position");
			
			System.out.printf("%s to be placed at %s%n", parsedPiece, parsedPosition);
		}
	}
	
	//////TEST METHOD: Parses movement//////
	public void testParsePieceMovement()
	{
		//Change matcher to parse text file by end of module 1
		Pattern movement = Pattern.compile("(?<initPosition>[a-hA-H][1-8]) (?<finalPosition>[a-hA-H][1-8])");
		Matcher matcher = movement.matcher("a1 a8");
		System.out.println("Matching...");
		while(matcher.find())
		{
			String parsedInitPosition = matcher.group("initPosition");
			String parsedFinalPosition = matcher.group("finalPosition");
			
			System.out.printf("Piece on %s moved to %s%n", parsedInitPosition, parsedFinalPosition);
		}
	}
	
	//////TEST METHOD: Checks for piece capturing//////
	public void testParsePieceCapture()
	{
		Pattern capture = Pattern.compile("(?<initPos>[a-h][1-8])\\s(?<finalPos>[a-h][1-8])\\*");
		System.out.println("Searching for piece captures...");
		Matcher matcher = capture.matcher("a1 a8*");
		
		if(matcher.find())
		{
			System.out.printf("Moved piece from %s to %s and captured piece on %s%n", matcher.group("initPos"), matcher.group("finalPos"), matcher.group("finalPos"));
		}
	}
	
	//////TEST METHOD: Parses castling//////
	public void testParseSpecialMovement()
	{
		//Change matcher to parse text file by end of module 1
		Pattern castle = Pattern.compile("([a-h][18])\\s([a-h][18])\\s([a-h][18])\\s([a-h][18])");
		Matcher matcher = castle.matcher("e1 g1 h1 f1");
		System.out.println("Matching...");
		if(matcher.find())
		{
			System.out.printf("Moved king and rook from positions %s and %s to positions %s and %s, respectively%n", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
		}
		System.out.println("");
	}
}
