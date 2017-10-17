package Board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Piece.Alliance;
import Piece.Bishop;
import Piece.King;
import Piece.Knight;
import Piece.Pawn;
import Piece.Piece;
import Piece.Queen;
import Piece.Rook;
import Player.BlackPlayer;
import Player.WhitePlayer;

public class Board {
	
	List<Tile> gameBoard; // main game board list which will store all the tiles on the board. 
	Collection<Piece> whitePieces; // will store all active white pieces at the current calling instance.
	Collection<Piece> blackPieces; // will store all active black pieces at the current calling instance.
	Collection<Move> whiteStandardLegalMoves;
	Collection<Move> blackStandardLegalMoves;
	
	BlackPlayer blackPlayer;
	WhitePlayer whitePlayer;
	
	
	public Board(Builder builder) { // constructor will be called from the Builder class build method.
	
		System.out.println("Board Constructor called ");
		this.gameBoard = createGameBoard(builder);
		this.blackPieces = createActivePieces(this.gameBoard , Alliance.BLACK);
		this.whitePieces = createActivePieces(this.gameBoard, Alliance.WHITE);
		this.blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
		this.whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
		this.blackPlayer = new BlackPlayer(this , whiteStandardLegalMoves , blackStandardLegalMoves);
		this.whitePlayer = new WhitePlayer(this , whiteStandardLegalMoves , blackStandardLegalMoves);
		
	
	}
	
	public Collection<Piece> getBlackPieces(){
		return this.blackPieces;
	}
	
	public Collection<Piece> getWhitePieces(){
		return this.whitePieces;
	}
	
	 private static String prettyPrint(final Tile tile) {
	        
	        return tile.toString();
	    }
	  @Override
	    public String toString() {
	        final StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
	            final String tileText = prettyPrint(this.gameBoard.get(i));
	            builder.append(String.format("%3s", tileText));
	            if ((i + 1) % 8 == 0) {
	                builder.append("\n");
	            }
	        }
	        return builder.toString();
	    }
	
	  public Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
		

			System.out.println("Calculating legal moves ...");
		final List<Move> legalMoves = new ArrayList<Move>();
		for(Piece piece : pieces){
			System.out.println("Legal moves are ");
			legalMoves.addAll(piece.calculateLegalMoves(this));
			System.out.println("Legal moves of "+piece + " are "+legalMoves.toString());
		}

		
		return legalMoves;
	}

	public Collection<Piece> createActivePieces(List<Tile> gameBoard, // arraylist to store all the active pieces currently
			Alliance alliance) {

		System.out.println("Creating active pieces ");
		
		List<Piece> activePieces = new ArrayList<Piece>();
		
		for(Tile tile:gameBoard  ){
			if(tile.isTileOccupied()){
				Piece piece = tile.getPiece();
				if(piece.getAlliance() == alliance ){
					activePieces.add(piece);
				}
			}
		}

		System.out.println(" Active pieces are "+activePieces.toString());
		
		
		return Collections.unmodifiableList(activePieces); // cannot change the contents of the list.
	}

	public static Board createStandardBoard(){ // set the initial positions 
		Builder builder = new Builder();
		 // Black Layout

		System.out.println(" in create standard board...");
		
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        //white to move
        builder.setMoveMaker(Alliance.WHITE);
        //build the board
        return builder.build();
	}


	
	public List<Tile> createGameBoard(Builder builder) {
		
		System.out.println(" Creating gameboard");
		
		List<Tile> listOfTiles = new ArrayList<Tile>();
		
		Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
		for(int i=0 ; i<BoardUtils.NUM_TILES ; i++){
			
			tiles[i] = Tile.createTile(i , builder.boardConfig.get(i)); //will check if the tile is occupied 
			// if its occupied the will return the object of OccupiedTile class else will return the cached copy
			//of Empty tile collection
			listOfTiles.add(tiles[i]);
			
		}

		System.out.println("list of tiles is  "+listOfTiles.toString());
		
		return Collections.unmodifiableList(listOfTiles);
	}



	public Tile getTile(int candidateCoord){
		return gameBoard.get(candidateCoord);
		
	}
	
	
	public static class Builder{ // helper class o build board modules.
		
		Map<Integer , Piece > boardConfig; // will store what piece is stored on each tile 
		Alliance nextMoveMaker;
		
		public Builder(){
			this.boardConfig = new HashMap<Integer, Piece>();
			
		}
		
		public Builder setPiece(final Piece piece){
			
			this.boardConfig.put(piece.getPiecePosition(), piece);
			return this;
		}
		
		public Builder setMoveMaker(final Alliance nextMoveMaker){
			this.nextMoveMaker = nextMoveMaker;
			return this;
			
		}
		
		public Board build(){
			System.out.println("In build method");
			
			return new Board(this);
			
		}
	}

}
