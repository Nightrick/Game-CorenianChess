package com.chess.engine.pieces;

import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
	
	protected final PieceType pieceType;
	protected final int piecePosition;
	// pieceAlliance is to be treated as an enum, which is a special Java type used to define collections of constants. More precisely, a Java enum type is a special kind of Java class
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
		this.pieceType = pieceType;
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.isFirstMove = false;
	}
	
	public int getPiecePosition() {
		return this.piecePosition;
	}
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	
	public PieceType getPieceType() {
		return this.pieceType;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	public enum PieceType {
		
		PAWN("P") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				return true;
			}
		};
		
		private String pieceName;
		
		PieceType(String pieceName){
			this.pieceName = pieceName;
			
		} 
		
		@Override 
		public String toString() {
			return this.pieceName;
		}	
		
		public abstract boolean isKing();
	}

}
