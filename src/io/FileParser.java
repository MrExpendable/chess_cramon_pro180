package io;
import java.util.regex.*;

public class FileParser 
{
	//Parses placement
	public void parsePiecePlacement()
	{
		Pattern placement = Pattern.compile("(?<piece>[BKNPQR][dl])(?<position>[a-hA-H][1-8])");
		Matcher matcher = placement.matcher("Rla1");
		System.out.println("Matching...");
		while(matcher.find())
		{
			String parsedPiece = matcher.group("piece");
			String parsedPosition = matcher.group("position");
		}
	}
	
	//Parses movement
	public void parsePieceMovement()
	{
		
	}
	
	//Parses castling
	public void parseSpecialMovement()
	{
		
	}
}
