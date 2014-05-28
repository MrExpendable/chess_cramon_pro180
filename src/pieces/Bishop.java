package pieces;

public class Bishop extends Piece
{
	public Bishop(boolean isPieceWhite)
	{
		isWhite = isPieceWhite;
		name = "B";
	}
	
	public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow)
	{
		final int MAXBOUNDS = 8;
		return (toCol != fromCol && fromRow != toRow && ((Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) || Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol)));
	}
}
