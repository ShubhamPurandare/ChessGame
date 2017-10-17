package Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Tile;
import Board.Move.AttackMove;
import Board.Move.Majormove;
import Piece.Piece.PieceType;

public class King extends Piece{

	
	public final static int[] CANDIDATE_MOVES_COORDINATES = {-9,-8,-7,-1,1, 7,8,9};
	
	public King( Alliance pieceAlliance, int pieceCoordinate) {
		super(pieceCoordinate, pieceAlliance, PieceType.KING);
		// TODO Auto-generated constructor stub
		System.out.println("Constructing King...of alliance  "+pieceAlliance.toString());
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		// TODO Auto-generated method stub
		ArrayList<Move > legalMoves = new ArrayList<Move>();
		for(final int currentCandidate : CANDIDATE_MOVES_COORDINATES){ // looping through all possible coordinates where knight can now go..
			
			int coordinatedestn = currentCandidate + this.pieceCoordinate; // calculate the final coordinate when he goes at that posn
			
			if(BoardUtils.isvalidCoordinate(coordinatedestn)){ // check if its on board
				
				if(isFirstColoumnExclusion(this.pieceCoordinate, currentCandidate)
						|| isEighthColoumnExclusion(this.pieceCoordinate, currentCandidate))
				{
					continue;
					
				}
				
				Tile canndidateTile = board.getTile(coordinatedestn); // Get the tile at that position
				
				if(!canndidateTile.isTileOccupied()){ // if tile has no piece then its a legal move
					legalMoves.add(new Majormove(board , this,coordinatedestn ));
				
					
					
				}else{
					Piece candidatePiece = canndidateTile.getPiece(); // tile has a piece so get that piece
					
					if(this.pieceAlliance != candidatePiece.getAlliance()){ 
						
						legalMoves.add(new AttackMove(board, this, coordinatedestn, candidatePiece)); // piece on that tile is not of the same alliance as that of the calling piece
					}
				}	
			}
		}// end of for
		return Collections.unmodifiableCollection(legalMoves);
	}
	public String toString(){
		return PieceType.KING.toString();
	}
	
	private static boolean isFirstColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.FIRST_COLOUMN[currentPosn] && ( offset == -9 || offset == -1 || offset== 7 );
	}
	
	private static boolean isEighthColoumnExclusion(int currentPosn, final int offset){
	
		return BoardUtils.EIGHT_COLOUMN[currentPosn] && ( offset == -7 || offset == 1 || offset== 9 );
	}


}
