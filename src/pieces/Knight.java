package pieces;

import board.Chessboard;

public class Knight extends Piece
{
	public Knight(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "N";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		return ((Math.abs(fromCol - toCol) == 2 && Math.abs(fromRow - toRow) == 1) ||
				(Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 2));
	}

	@Override
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow,
			Chessboard copyBoard) {
		// TODO Auto-generated method stub
		return false;
	}
}
