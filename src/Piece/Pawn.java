package Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Move.Majormove;
import Piece.Piece.PieceType;

public class Pawn extends Piece{

	
	public static final int[] CANDIDATE_VECTOR_OFFSET = {8,16 , 7, 9}; 
	/*
	 * 8 --> normal one step ahead 
	 * 16 --> two steps ahead when its the 1st move
	 * 7 --> left attack
	 * 9 --> right attack 
	  */
	public Pawn( Alliance pieceAlliance, int pieceCoordinate) {
		super(pieceCoordinate, pieceAlliance , PieceType.PAWN);
		System.out.println("Constructing Pawn...of alliance  "+pieceAlliance.toString());
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		// TODO Auto-generated method stub
		final List<Move> legalMoves = new ArrayList<Move>();
		// logic
		
		for(final int coordinateOffset : CANDIDATE_VECTOR_OFFSET){
			// will decide the direction 
			// we are working of a 1D array hence black will move 8 places forward and white will move 8 places backwards
			int candidateDestnCoord = this.pieceCoordinate + (this.pieceAlliance.getDirection() * coordinateOffset);
			
			if(!BoardUtils.isvalidCoordinate(candidateDestnCoord)){
				continue;
			}
			
			
			if(coordinateOffset == 8 && !board.getTile(candidateDestnCoord).isTileOccupied()){
				// pawn can move one place ahead ... and black and white will move in opposite direction hence 
				//the getDirection will give 1 or -1
				legalMoves.add(new Majormove(board , this , candidateDestnCoord ));
				
				
				// pawn can move 2 places ahead only on its first move ...
			}else if (coordinateOffset == 16 && this.isFirstMove &&  
					( (BoardUtils.ROW_TWO[this.pieceCoordinate] && this.pieceAlliance.isWhite() ) ||
					(BoardUtils.ROW_SEVEN[this.pieceCoordinate]  && this.pieceAlliance.isBlack()) ) ){
				// black pawn on row seven and white on 2
				
				// calculate the piece at the behind if we jump 2 places ahead
				int behindCoordDetn = this.pieceCoordinate + (this.pieceAlliance.getDirection() * 8);
				
				if(!board.getTile(candidateDestnCoord).isTileOccupied() && !board.getTile(behindCoordDetn).isTileOccupied()){
					// both places are not occupied when jumping 2 positions...
					legalMoves.add(new Majormove(board , this , candidateDestnCoord ));
					
					
				}
				
				
			}else if(coordinateOffset == 7 &&
					!( BoardUtils.FIRST_COLOUMN[this.pieceCoordinate] && this.pieceAlliance.isBlack() ||
					  BoardUtils.EIGHT_COLOUMN[this.pieceCoordinate] && this.pieceAlliance.isWhite())){ // attacking move by 7
				// offset is 7 and white is not on column 8 or black is not on column 1
				
				if(board.getTile(candidateDestnCoord).isTileOccupied() ){ // check is tile is occupied
					
					Piece destnPiece = board.getTile(candidateDestnCoord).getPiece();
					if(this.pieceAlliance != destnPiece.pieceAlliance){
						// the piece is of the opponent hence we can attack..
						legalMoves.add(new Majormove(board , this , candidateDestnCoord ));
							
					}
					
				}	
			}else if(coordinateOffset == 9 &&
					!( BoardUtils.FIRST_COLOUMN[this.pieceCoordinate] && this.pieceAlliance.isWhite() ||
							  BoardUtils.EIGHT_COLOUMN[this.pieceCoordinate] && this.pieceAlliance.isBlack())){ 
						// attacking move by 9
						// offset is 9 and white is not on column 1 or black is not on column 8
						
						if(board.getTile(candidateDestnCoord).isTileOccupied() ){ // check is tile is occupied
							
							Piece destnPiece = board.getTile(candidateDestnCoord).getPiece();
							if(this.pieceAlliance != destnPiece.pieceAlliance){
								// the piece is of the opponent hence we can attack..
								legalMoves.add(new Majormove(board , this , candidateDestnCoord ));
									
							}
							
						}	
					} 
		}
		
		
		return Collections.unmodifiableCollection(legalMoves);
	}
	
	public String toString(){
		return PieceType.PAWN.toString();
	}

}
