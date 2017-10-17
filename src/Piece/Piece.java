package Piece;

import java.util.Collection;
import java.util.List;

import Board.Board;
import Board.Move;


public abstract class Piece {
	
	protected int pieceCoordinate;     // Tile Coordinate of the piece
	protected Alliance pieceAlliance;  //  BLACK OR WHITE
	protected boolean isFirstMove;
	protected PieceType pieceType;
	
	Piece(final int pieceCoordinate, final Alliance pieceAlliance , final PieceType pieceType){
		this.pieceAlliance = pieceAlliance;
		this.pieceCoordinate = pieceCoordinate;
		this.pieceType = pieceType;
	}
	
	public PieceType getPieceType(){
		return this.pieceType;
	}
	
	public Alliance getAlliance(){
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove(){
		return this.isFirstMove;
	}
	
	public int getPiecePosition(){
		return this.pieceCoordinate;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board); // This method will be overridden by particular Piece class eg . Knight
	// This method will calculate all possible legal moves on each point for each piece. 

	public enum PieceType{
	
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return true;
			}
		},
		PAWN("P") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	
		private String pieceName;
		
		PieceType(final String pieceName){
			
			this.pieceName = pieceName;
		}
		
		@Override
		public String toString(){
			return this.pieceName;
		}
		
		public abstract boolean isKing();
		
	}
	
}




