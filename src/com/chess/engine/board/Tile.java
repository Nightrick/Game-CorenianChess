package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
	
	//The protected access modifier means this int can only be accessed by its subclasses. The final modifier means that once this memberfield is set inside the constructor, it cannot be changed. 
	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private Tile(final int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();		
		
		for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i,  new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	public abstract boolean isTileOccupied();
	//An abstract method is a method declared without implementation (hence the ;) 
	//If the class includes abstract methods, then it must be declared abstract as well
	//Abstract classes cannot be instantiated, but they can be subclassed, wherein the subclass will usually provide all implementation for the abstract methods of its parent. 
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile {
		
		private EmptyTile(final int coordinate){
			super(coordinate); //Here, the keyword super indicates the coordinate being referenced is the super-class or parent variable. 
		}
		
		@Override
		public String toString() {
			return "-";
		}
		
		//Overriding is a feature that allows a subclass or child class to provide a specific implementation of a method that is already provided by one of its super-classes or parent classes.
		//When a method in a subclass has the same name, same parameters or signature, and same return type (or sub-type) as a method in its super-class, the method in the subclass is said to override the method in the super class
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
		
	}
	
	public static final class OccupiedTile extends Tile {
		//Private means there's no way to access this variable outside of this class unless you use a getter method
		private final Piece pieceOnTile;
		
		private OccupiedTile(int tileCoordinate, Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		//Black pieces will show up as lower case, white pieces as upper case. This is simply a convention. 
		@Override
		public String toString() {
			return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
				   getPiece().toString();
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
		
	}

}
