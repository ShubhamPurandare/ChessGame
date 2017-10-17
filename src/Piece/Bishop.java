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
import Piece.Piece.PieceType;


public class Bishop extends Piece {
	
	public static final int[] CANDIDATE_VECTOR_OFFSET = {-9,-7,7,9};

	public Bishop( Alliance pieceAlliance, int pieceCoordinate) {
		super(pieceCoordinate, pieceAlliance , PieceType.BISHOP);
		// TODO Auto-generated constructor stub
		System.out.println("Constructing Bishop...of alliance  "+pieceAlliance.toString());
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		// TODO Auto-generated method stub
		
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for(final int coordinateOffset : CANDIDATE_VECTOR_OFFSET){
			
			int destncoordinate = this.pieceCoordinate;
			while(BoardUtils.isvalidCoordinate(destncoordinate)){
				
				// logic fails when its the 1st or 8th column and we do -9 or -7 hence a check for it
				
				if(isFirstColoumnExclusion(destncoordinate, coordinateOffset)||
						isEighthColoumnExclusion(destncoordinate, coordinateOffset)){
					break; // test case fails
				}
				
				destncoordinate += coordinateOffset;
				if(BoardUtils.isvalidCoordinate(destncoordinate)){
					// same code now as of Knight..
					Tile canndidateTile = board.getTile(destncoordinate); // Get the tile at that position
					
					if(!canndidateTile.isTileOccupied()){ // if tile has no piece then its a legal move
						legalMoves.add(new Majormove(board , this,destncoordinate ));
					
						
						
					}else{
						Piece candidatePiece = canndidateTile.getPiece(); // tile has a piece so get that piece
						
						if(this.pieceAlliance != candidatePiece.getAlliance()){ 
							
							legalMoves.add(new AttackMove(board, this, destncoordinate, candidatePiece)); // piece on that tile is not of the same alliance as that of the calling piece
						}
						break; // if we have the opponent then bishop cannot move on to next positions in line . hence break the loop
					}
				}
				
			}
			
		}// end of for.
		
		return Collections.unmodifiableCollection(legalMoves);
	}
	
	public String toString(){
		return PieceType.BISHOP.toString();
	}
	
	
	private static boolean isFirstColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.FIRST_COLOUMN[currentPosn] && ( offset == -9 || offset == 7 );
	}
	
	private static boolean isEighthColoumnExclusion(int currentPosn, final int offset){
		
		return BoardUtils.EIGHT_COLOUMN[currentPosn] && ( offset == -7 || offset == 9 );
	}

}
