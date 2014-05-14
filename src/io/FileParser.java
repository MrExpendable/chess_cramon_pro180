package io;
import java.util.regex.*;

public class FileParser 
{
	//Parses placement
	public void parsePiecePlacement()
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
	
	//Parses movement
	public void parsePieceMovement()
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
	
	//Parses castling
	public void parseSpecialMovement()
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
