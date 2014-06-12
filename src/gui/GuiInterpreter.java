package gui;

import board.*;
import game.GameManager;

import java.util.ArrayList;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/*This class acts as a controller that handles input from ChessGameWindow and sends it to GameManager*/
public class GuiInterpreter 
{
	private Chessboard guiChessboard;
	private GameManager gm;
	public final int BOARD_LENGTH = 8;
	
	public GuiInterpreter(String cmdArgs)
	{
		gm = new GameManager(cmdArgs);
		guiChessboard = new Chessboard(gm.getChessboard());
		
		printBoard();
	}
	
	public Chessboard getChessboard()
	{
		return guiChessboard;
	}
	
	//makeMove
	
	public ArrayList<Location> getAvailableMoves(Location l)
	{
		Piece p = guiChessboard.getSquares()[l.getRow()][l.getColumn()].getPiece();
		final int BOARD_LENGTH = 8;
		Chessboard copyOfBoard = new Chessboard(guiChessboard);
		ArrayList<Location> toReturn = new ArrayList<Location>();
		Piece oldPiece = p;
		oldPiece = determinePieceType(p);
		
		if(p != null)
		{			
			for(int i = 0; i < BOARD_LENGTH; i++)
			{
				for(int j = 0; j < BOARD_LENGTH; j++)
				{
					Piece pieceBeforeMod = p;
					pieceBeforeMod = determinePieceType(p);
					
					int pieceCol = p.getLocation().getColumn();
					int pieceRow = p.getLocation().getRow();
					
					if(!p.isMoveObstructed(pieceCol, pieceRow, i, j, copyOfBoard))
					{
						if(p.isValidMove(pieceCol, pieceRow, i, j, copyOfBoard))
						{
							Location possibleMove = new Location(j, i);
							toReturn.add(possibleMove);
							p = pieceBeforeMod;
							//squares[pieceRow][pieceCol].setPiece(pieceBeforeMod);
						}
					}
				}
			}
		}
		
		p = oldPiece;
		return toReturn;
	}
	
	public Piece determinePieceType(Piece p)
	{
		if(p instanceof Pawn)
		{
			Pawn toReturn = (Pawn)p;
			return new Pawn(toReturn);
		}
		else if(p instanceof Knight)
		{
			Knight toReturn = (Knight)p;
			return new Knight(toReturn);
		}
		else if(p instanceof Queen)
		{
			Queen toReturn = (Queen)p;
			return new Queen(toReturn);
		}
		else if(p instanceof Rook)
		{
			Rook toReturn = (Rook)p;
			return new Rook(toReturn);
		}
		else if(p instanceof Bishop)
		{
			Bishop toReturn = (Bishop)p;
			return new Bishop(toReturn);
		}
		else if(p instanceof King)
		{
			King toReturn = (King)p;
			return new King(toReturn);
		}
		else
		{
			//shouldn't reach this, only adding this to satisfy the java gods
			return null;
		}
	}
	
	//Print the board
	public void printBoard()
	{
		System.out.println("\n       a   b   c   d   e   f   g   h\n");
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			//Prints out the numbers on the side of the board
			System.out.print(i + 1 + "      ");
			
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				if(guiChessboard.getSquares()[i][j].getPiece() == null)
				{
					//If the element is null, print - instead to signify an empty space
					System.out.print("-   ");
				}
				else
				{
					//Else, check the type of the piece in the array, and print it out
					System.out.print(guiChessboard.getSquares()[i][j].toString() + "  ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
