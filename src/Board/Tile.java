package Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import Piece.Piece;

public abstract class Tile { // Immutable base class for empty tiles and occupied tiles . Chess board will be a 1D array of 64 elements 
	
	protected final int tileCoordinate; // protected as it should only be called by derived 
	//classes and once declared should not be modified hence final. 
	
	private static Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles(); // cached copy of empty tiles in a hash map
	// we wont create empty tile again and again we would return object of the hash map when required
	
	Tile(int tileCoordinate){
		
		this.tileCoordinate = tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		
		Map<Integer, EmptyTile> emptyTileMap = new HashMap<Integer, Tile.EmptyTile>();
		for(int i=0; i< BoardUtils.NUM_TILES; i++){
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		 return Collections.unmodifiableMap(emptyTileMap); // contents of emptyTileMap cannot be modified now..
		
	}
	
	public static Tile createTile(int tileCoordinate , Piece piece){ // check if piece is empty or not 
		

	//	System.out.println("In create tile");
		return ( piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate));
		
	}

	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	public abstract String toString();
	
	
	public static final class EmptyTile extends Tile{
		
		 EmptyTile(final int tileCoordinate){
			super(tileCoordinate);
		}

		@Override
		public boolean isTileOccupied() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Piece getPiece() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "-";
		}
		
	} 
	
	public static final class OccupiedTile extends Tile{

		Piece pieceOnTile;
		
		 OccupiedTile(final int tileCoordinate , final Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isTileOccupied() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public Piece getPiece() {
			// TODO Auto-generated method stub
			return this.pieceOnTile;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getPiece().getAlliance().isBlack() ? getPiece().toString().toLowerCase() :
				 getPiece().toString();
		}
		
		
		
	}/// end of OccupiedTile class

}// end of Tile Class
