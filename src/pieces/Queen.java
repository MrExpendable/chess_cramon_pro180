package pieces;

public class Queen extends Piece
{
	public Queen(boolean isPieceWhite)
	{
		pieceType = PieceType.Queen;
		isWhite = isPieceWhite;
		name = "Q";
	}
}
