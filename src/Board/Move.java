package Board;

import Piece.Piece;

public class Move {
	
	final Board board;
	final Piece movedpiece;
	final int destnCoordinate;
	
	public Move(Board board, Piece movedpiece,int destnCoordinate ){
		
		this.board = board;
		this.destnCoordinate = destnCoordinate;
		this.movedpiece = movedpiece;
	}
	
	public static final class Majormove extends Move{
		
		public Majormove(final Board board,final Piece movedpiece,final int destnCoordinate){
			super(board, movedpiece,destnCoordinate );
		}

	

		
		
	}
	
	public static final class AttackMove extends Move{
		
		final Piece attackedPiece;
		
		public AttackMove(final Board board,final Piece movedpiece,final int destnCoordinate 
				, final Piece attackedPiece){
			
			super(board, movedpiece,destnCoordinate );
			this.attackedPiece = attackedPiece;
		}
	}
	

}
