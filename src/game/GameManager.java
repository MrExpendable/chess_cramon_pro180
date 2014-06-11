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
					
					ArrayList<Piece> friendlyPieces = getFriendlyPieces(game.getBoard().getSquares(), player1.isPlayerWhite());
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
				}
			}
			else
			{
				if(isKingInCheck(false))
				{
					System.out.println("Player 2, your king is in check.");
					
					ArrayList<Piece> friendlyPieces = getFriendlyPieces(game.getBoard().getSquares(), player2.isPlayerWhite());
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
	public ArrayList<Piece> getFriendlyPieces(board.Square[][] squaresCopy, boolean isPieceWhite)
	{
		ArrayList<Piece> toReturn = new ArrayList<Piece>();
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				Piece currentPiece = squaresCopy[j][i].getPiece();
				//If the space doesn't have a piece or if the space has a piece that isn't its color
				if(currentPiece != null && currentPiece.isWhite == isPieceWhite)
				{
					toReturn.add(currentPiece);
				}
			}
		}
		
		return toReturn;
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
				Location initialPieceLocation = p.getLocation();
				int initRow = initialPieceLocation.getRow();
				int initCol = initialPieceLocation.getColumn();
				int finRow = l.getRow();
				int finCol = l.getColumn();
				if(!p.isMoveObstructed(initCol, initRow, finCol, finRow, board))
				{
					if(p.isValidMove(initCol, initRow, finCol, finRow, board))
					{
						//force piece back to its original location, not by movement, because movement logic still applies in this case
						//move, unmove, then check if king is not in check
						Piece endPiece = board.getSquares()[finRow][finCol].getPiece();
						board.getSquares()[finRow][finCol].setPiece(p);
						board.getSquares()[initRow][initCol].setPiece(null);
						boolean inCheck = isKingInCheck(isWhite);
						board.getSquares()[initRow][initCol].setPiece(p);
						board.getSquares()[finRow][finCol].setPiece(endPiece);
						if(!inCheck)
						{
							return false;
						}
					}
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
