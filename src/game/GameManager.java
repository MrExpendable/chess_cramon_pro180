package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pieces.Piece;
import board.Chessboard;
import board.Location;

public class GameManager 
{
	private Game game;
	private Player player1, player2;
	private boolean isPlayer1Turn = true;
	private boolean isRunning = true;
	public static final int BOARD_LENGTH = 8;
	
	public GameManager(String path)
	{
		game = new Game();
		player1 = new Player(true);
		player2 = new Player(false);
		game.startGame(path);
	}
	
	public void runGame()
	{
		//Include forfeit method, like making the king kill itself?
		while(isRunning)
		{
			//if player 1's turn
			if(isPlayer1Turn)
			{
				//Detect check here
				if(isKingInCheck(true))
				{
					System.out.println("Player 1, your king is in check.");
//					if(isKingInCheckmate(true))
//					{
//						System.out.println("Sorry Player 1, but your king is checkmated. Player 2 wins!");
//						isRunning = !isRunning;
//						break;
//					}
					ArrayList<Piece> friendlyPieces = getFriendlyPieces(game.getBoard().getSquares(), player1.isPlayerWhite());
					//ArrayList<Piece> offendingPieces = getOffendingPieces(game.getBoard().getSquares(), player1.isPlayerWhite());
					if(isInCheckmate(player1.isPlayerWhite(), game.getBoard(), friendlyPieces))
					{
						System.out.println("Player 1, your king is checkmated. Player 2 wins.");
						isRunning = !isRunning;
						break;
					}
				}
				
				System.out.println("Player 1's turn");
				//if player 1's turn is successful, change player turn
				if(player1.takeTurn(game.getBoard()))
				{
					isPlayer1Turn = !isPlayer1Turn;
					
					//after player 1's turn, see if the king is in checkmate
//					ArrayList<Piece> offendingPieces = getOffendingPieces(game.getBoard().getSquares(), player1.isPlayerWhite());
//					if(isInCheckmate(player1.isPlayerWhite(), game.getBoard(), offendingPieces))
//					{
//						System.out.println("Player 1, your king is checkmated. Player 2 wins.");
//						isRunning = !isRunning;
//						break;
//					}
				}
			}
			else
			{
				if(isKingInCheck(false))
				{
					System.out.println("Player 2, your king is in check.");
//					if(isKingInCheckmate(false))
//					{
//						System.out.println("Sorry Player 2, but your king is checkmated. Player 1 wins!");
//						isRunning = !isRunning;
//						break;
//					}
					ArrayList<Piece> friendlyPieces = getFriendlyPieces(game.getBoard().getSquares(), player2.isPlayerWhite());
					//ArrayList<Piece> offendingPieces = getOffendingPieces(game.getBoard().getSquares(), player2.isPlayerWhite());
					if(isInCheckmate(player2.isPlayerWhite(), game.getBoard(), friendlyPieces))
					{
						System.out.println("Player 2, your king is checkmated. Player 1 wins.");
						isRunning = !isRunning;
						break;
					}
				}
				
				System.out.println("Player 2's turn");
				if(player2.takeTurn(game.getBoard()))
				{
					isPlayer1Turn = !isPlayer1Turn;
					
					//after player 2's turn, see if the king is in checkmate
//					ArrayList<Piece> offendingPieces = getOffendingPieces(game.getBoard().getSquares(), player2.isPlayerWhite());
//					if(isInCheckmate(player2.isPlayerWhite(), game.getBoard(), offendingPieces))
//					{
//						System.out.println("Player 2, your king is checkmated. Player 1 wins.");
//						isRunning = !isRunning;
//						break;
//					}
				}
			}
			
			game.getBoard().printBoard();
		}
	}
	
	//Get list of all places that king could possibly move to
	public ArrayList<Location> getSafeAreas(board.Square[][] squaresCopy, Location kingLoc, boolean isKingWhite)
	{
		ArrayList<Location> toReturn = new ArrayList<Location>();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				Location possibleSafeArea = new Location(j, i);
				Piece currentPiece = squaresCopy[j][i].getPiece();
				
				//If the space doesn't have a piece or if the space has a piece that isn't its color
				if(currentPiece == null || currentPiece.isWhite != isKingWhite)
				{ 
					//if king can move to this space
					if(game.getBoard().canKingEscape(kingLoc, possibleSafeArea))
					{
						toReturn.add(possibleSafeArea);
					}
				}
			}
		}
		
		return toReturn;
	}
	
	//Get list of all pieces that could capture king
	public ArrayList<Piece> getOffendingPieces(board.Square[][] squaresCopy, boolean isWhite)
	{
		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				Piece currentPiece = squaresCopy[j][i].getPiece();
				//If the space doesn't have a piece or if the space has a piece that isn't its color
				if(currentPiece != null && currentPiece.isWhite != isWhite)
				{
					toReturn.add(currentPiece);
				}
			}
		}
		
		return toReturn;
	}
	
	//Get list of all pieces that could capture king
	public ArrayList<Piece> getFriendlyPieces(board.Square[][] squaresCopy, boolean isWhite)
	{
		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				Piece currentPiece = squaresCopy[j][i].getPiece();
				//If the space doesn't have a piece or if the space has a piece that isn't its color
				if(currentPiece != null && currentPiece.isWhite == isWhite)
				{
					toReturn.add(currentPiece);
				}
			}
		}
		
		return toReturn;
	}
	
//	//Detect if an opposing piece can move into a king's safe areas
//	public ArrayList<Location> canOpposingPieceCaptureKing(ArrayList<Location> possibleSafeAreas, ArrayList<Location> actualSafeAreas, ArrayList<Piece> offendingPieces)
//	{
//		ArrayList<Location> toReturn = new ArrayList<Location>();
//		
//		//for every location
//		for(Location l : possibleSafeAreas)
//		{
//			//for every piece
//			for(Piece p : offendingPieces)
//			{
//				//if this piece
//			}
//		}
//		
//		return toReturn;
//	}
	
//	public boolean evaluateCheckmate(ArrayList<Location> possibleSafeAreas, ArrayList<Piece> offendingPieces)
//	{
//		for(Location l : possibleSafeAreas)
//	    {
//	    	for(Piece p : offendingPieces)
//	    	{
//	    		Location pieceLoc = p.getLocation();
//	    		if(p.isValidMove(pieceLoc.getColumn(), pieceLoc.getRow(), l.getColumn(), l.getRow(), game.getBoard()))
//	    		{
//	    			possibleSafeAreas.remove(l);
//	    		}
//	    	}
//	    	System.out.printf("Safe areas for king to move to - %s%n", l.toString());
//	    }
//	}
	
	//Detect whether a king is in checkmate or not
	public boolean isKingInCheckmate(boolean isKingWhite)
	{
		/*
		 * Ideas for checkmate
		 * After a piece moves, check for checkmate depending on that piece's color
		 * For each piece of that color
		 * For each possible move of that piece
		 * Try and move that piece
		 * If the king isn't in check, then the king isn't in checkmate
		 * Reset the move
		 */
		//Create copies of the board at its current state so that I'm not modifying the game
		Chessboard chessboardCopy = new Chessboard(game.getBoard());
		board.Square[][] squaresCopy = chessboardCopy.getSquares();
		
		Location kingLoc = getKingLocation(isKingWhite);
		ArrayList<Piece> offendingPieces = getOffendingPieces(squaresCopy, isKingWhite);
		ArrayList<Piece> friendlyPieces = getFriendlyPieces(squaresCopy, isKingWhite);
		ArrayList<Location> possibleSafeAreas = getSafeAreas(squaresCopy, kingLoc, isKingWhite);
		//I'm using addAll to prevent a ConcurrentModificationException, since setting the safeAreas ArrayList to possibleSafeAreas causes this exception
		ArrayList<Location> safeAreas = new ArrayList<Location>();
		safeAreas.addAll(possibleSafeAreas);
		
		//if there are some safe areas
		//set the king's current space to null to avoid obstruction
		squaresCopy[kingLoc.getRow()][kingLoc.getColumn()].setPiece(null);
		for(Location l : possibleSafeAreas)
	    {
	    	for(Piece p : offendingPieces)
	    	{
	    		Location pieceLoc = p.getLocation();
	    		//if the piece can move there, remove the location from the safe areas
	    		if(p.isValidMove(pieceLoc.getColumn(), pieceLoc.getRow(), l.getColumn(), l.getRow(), game.getBoard()))
	    		{
	    			safeAreas.remove(l);
	    		}
	    	}
	    }
		//set the king's space back to a king
		squaresCopy[kingLoc.getRow()][kingLoc.getColumn()].setPiece(new pieces.King(isKingWhite, kingLoc));
		
		//if there are safe areas, print them out and return false
		if(!safeAreas.isEmpty())
		{
			System.out.println("Safe areas for king to move to: ");
			for(Location l : safeAreas)
			{
				l.toString();
			}
			
			return false;
		}
		else
		{
			System.out.println("No safe areas available for the king to move to, checking to see if a friendly piece can prevent checkmate...");
			//see if any friendly pieces can move to help the king
		}
	    
		return false;
	}
	
	//Returns a king's location
	public Location getKingLocation(boolean isPieceWhite)
	{
		Location toReturn = null;
		board.Square[][] board = game.getBoard().getSquares();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				//If the space has a piece
				if(board[j][i].getPiece() != null)
				{
					//if piece is a king and is the color specified by boolean that is passed in
					if(board[j][i].getPiece() instanceof pieces.King && board[j][i].getPiece().isWhite == isPieceWhite)
					{
						//Get the king and set the variable to be returned to the king's location
						toReturn = new Location(j, i);
					}
				}
			}
		}
		
		return toReturn;
	}
	
	public boolean isKingInCheck(boolean isKingWhite)
	{
		//Create copies of the board at its current state so that I'm not modifying the game
		Chessboard chessboardCopy = new Chessboard(game.getBoard());
		board.Square[][] squaresCopy = chessboardCopy.getSquares();
		
		Location kingLoc = getKingLocation(isKingWhite);
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				//If there's a piece there and piece is not same color as king
				if(squaresCopy[j][i].getPiece() != null && squaresCopy[j][i].getPiece().isWhite != isKingWhite)
				{
					//if piece can move to king
					if(chessboardCopy.detectCheck(new Location(j, i), kingLoc))
					{
						return true;
					}
				}
			}
		}
		//return false if king isn't found to be in check
		return false;
	}
	
	public boolean isInCheckmate(boolean isWhite, Chessboard board, ArrayList<Piece> friendlyPieces/*ArrayList<Piece> offendingPieces*/)
	{
		board = new Chessboard(board);
		
		for(Piece p : friendlyPieces)
		{
			ArrayList<Location> friendlyPieceMoves = getAvailableMoves(p, board);
			for(Location l : friendlyPieceMoves)
			{
//				board.movePiece(p.getLocation(), l);
//				if(!isKingInCheck(isWhite))
//				{
//					board.movePiece(l, p.getLocation());
//					return false;
//				}
//				board.movePiece(l, p.getLocation());
				Location initialPieceLocation = p.getLocation();
				if(p.isValidMove(initialPieceLocation.getColumn(), initialPieceLocation.getRow(), l.getColumn(), l.getRow(), board))
				{
					board.movePiece(initialPieceLocation, l);
					if(!isKingInCheck(isWhite))
					{
						board.movePiece(p.getLocation(), initialPieceLocation);
						return false;
					}
					board.movePiece(p.getLocation(), initialPieceLocation);
				}
			}
		}
		
		return true;
	}
	
	public ArrayList<Location> getAvailableMoves(Piece p, Chessboard board)
	{
		final int BOARD_LENGTH = 8;
		board = new Chessboard(board);
		ArrayList<Location> toReturn = new ArrayList<Location>();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				if(p.isValidMove(p.getLocation().getColumn(), p.getLocation().getRow(), j, i, board))
				{
					toReturn.add(new Location(i, j));
				}
			}
		}
		
		return toReturn;
	}
}
