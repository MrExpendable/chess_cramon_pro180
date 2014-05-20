package pieces;

public class Pawn extends Piece
{
	public Pawn(boolean isPieceWhite)
	{
		pieceType = PieceType.Pawn;
		isWhite = isPieceWhite;
		name = "P";
	}
}
