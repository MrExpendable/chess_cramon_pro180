package io;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import board.Chessboard;

public class FileParser 
{
	private Chessboard board;
	
	public FileParser()
	{
		board = new Chessboard();
	}
	
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
				boolean isPieceWhite = parsedPiece.contains("l") ? true : false;
				
				board.fillBoard(parsedPosition, isPieceWhite, parsedPiece);
				
				//System.out.printf("%s to be placed at %s%n", parsedPiece, parsedPosition);
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
}
