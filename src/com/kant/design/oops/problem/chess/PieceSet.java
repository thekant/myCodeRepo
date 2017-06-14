/**
 * 
 */
package com.kant.design.oops.problem.chess;

import java.util.List;

/**
 * TODO add factory to create white and black piece set
 * 
 * @author shaskant
 *
 */
public class PieceSet {
	private PieceColor color;
	private List<Piece> pieces;

	/**
	 * @param color
	 * @param pieces
	 */
	public PieceSet(PieceColor color, List<Piece> pieces) {
		super();
		this.color = color;
		this.pieces = pieces;
	}

	/**
	 * @return the color
	 */
	public PieceColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(PieceColor color) {
		this.color = color;
	}

	/**
	 * @return the pieces
	 */
	public List<Piece> getPieces() {
		return pieces;
	}

	/**
	 * @param pieces
	 *            the pieces to set
	 */
	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
