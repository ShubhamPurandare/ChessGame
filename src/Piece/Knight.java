package Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Tile;
import Board.Move.AttackMove;
import Board.Move.Majormove;

public class Knight extends Piece{

	
	public final static int[] CANDIDATE_MOVES_COORDINATES = {-17,-15,-10,-6,6, 10,15,17};
	
	public Knight( Alliance pieceAlliance, int pieceCoordinate) {
		super(pieceCoordinate, pieceAlliance, PieceType.KNIGHT);
		System.out.println("Constructing Knight...of alliance  "+pieceAlliance.toString());
		
		
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
	
		ArrayList<Move > legalMoves = new ArrayList<Move>();
		for(final int currentCandidate : CANDIDATE_MOVES_COORDINATES){ // looping through all possible coordinates where knight can now go..
			
			int coordinatedestn = currentCandidate + this.pieceCoordinate; // calculate the final coordinate when he goes at that posn
			
			if(BoardUtils.isvalidCoordinate(coordinatedestn)){ // check if its on board
				
				if(isFirstColoumnExclusion(this.pieceCoordinate, currentCandidate)
						|| isSecondColoumnExclusion(this.pieceCoordinate, currentCandidate)
						|| isSeventhColoumnExclusion(this.pieceCoordinate, currentCandidate)
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
	
	@Override
	public String toString(){
		return PieceType.KNIGHT.toString();
	}
	
	
	
	// edge cases where the logic fails..
	
	private static boolean isFirstColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.FIRST_COLOUMN[currentPosn] && ( offset == -17 || offset == -10 || offset== 6 || offset == 15);
	}
	
	private static boolean isSecondColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.SECOND_COLOUMN[currentPosn] && ( offset == -10 || offset== 6 );
	}
	
	private static boolean isSeventhColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.SEVEN_COLOUMN[currentPosn] && ( offset == 10 || offset== -6 );
	}
	
	private static boolean isEighthColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.EIGHT_COLOUMN[currentPosn] && ( offset == 17 || offset == 10 || offset== -6 || offset == -15);
	}

}
