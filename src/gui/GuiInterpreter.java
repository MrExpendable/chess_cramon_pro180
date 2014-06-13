package gui;

import board.*;
import game.GameManager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	public boolean isPlayer1Turn = true;
	public final int BOARD_LENGTH = 8;
	private int buttonClickCount = 0;
	private Location[] playerMove = new Location[2];
	private String cmdArgs;
	
	//Creates a new GameManager and Chessboard
	public GuiInterpreter(String cmdArgs)
	{
		this.cmdArgs = cmdArgs;
		gm = new GameManager(cmdArgs);
		guiChessboard = new Chessboard(gm.getChessboard());
		
		printBoard();
	}
	
	/*=============== GETTERS AND SETTERS ===============*/
	
	//sets the player's choice of piece
	public void setPieceChoice(Location l)
	{
		playerMove[0] = l;
	}
	
	//sets the player's choice of move
	public void setMoveChoice(Location l)
	{
		playerMove[1] = l;
	}
	
	//hardcoded values are bad
	//gets the player's choice of piece
	public Location getPieceChoice()
	{
		return playerMove[0];
	}
	
	//hardcoded values are bad, but in this case, I don't care at the moment
	//gets the player's choice of move
	public Location getMoveChoice()
	{
		return playerMove[1];
	}
	
	//Increments the button click count
	public void incrementButtonClickCount()
	{
		buttonClickCount++;
	}
	
	//Returns button click count as it currently is
	public int getButtonClickCount()
	{
		return buttonClickCount;
	}
	
	//Reset the button click count back to 0
	public void resetButtonClickCount()
	{
		buttonClickCount = 0;
	}
	
	//Return if it's player 1's turn or not
	public boolean isPlayer1Turn()
	{
		return gm.isPlayer1Turn();
	}
	
	//Return the chessboard
	public Chessboard getChessboard()
	{
		return guiChessboard;
	}
	
	/*=============== METHODS THAT DO SOMETHING MEANINGFUL ===============*/
	
	public void changePlayerTurn()
	{
		isPlayer1Turn = !isPlayer1Turn;
	}
	
	public void checkPawnPromotion()
	{
		gm.checkPawnPromotion();
	}
	
	public boolean detectEndgame()
	{
		if(gm.isKingInCheck(true))
		{
			JOptionPane.showMessageDialog(null, "White king's in check");
			if(gm.isInCheckmate(true, guiChessboard, gm.getFriendlyPieces(guiChessboard.getSquares(), true)))
			{
				JOptionPane.showMessageDialog(null, "Player 1 in checkmate, player 2 wins!");
				return true;
			}
			return false;
		}
		else if(gm.isKingInCheck(false))
		{
			JOptionPane.showMessageDialog(null, "Black king's in check");
			if(gm.isInCheckmate(false, guiChessboard, gm.getFriendlyPieces(guiChessboard.getSquares(), false)))
			{
				JOptionPane.showMessageDialog(null, "Player 2 in checkmate, player 1 wins!");
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	//Makes a move based on player choice, provided the two inputs aren't null
	public boolean makeMove()
	{
		System.out.println("Piece choice: " + playerMove[0].toString());
		System.out.println("Move choice : " + playerMove[1].toString());
		
		if(gm.makeMove(playerMove[0], playerMove[1], isPlayer1Turn))
		{
			System.out.println("Successful move");
			printBoard();
			return true;
		}
		else
		{
			System.out.println("Failure");
			printBoard();
			return false;
		}
	}

	//Gets all available moves for a piece at a certain location
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
	
	//Determines a piece's type, only used by getAvailableMoves
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
	
	//Print the board, only need to do this for debugging now
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
