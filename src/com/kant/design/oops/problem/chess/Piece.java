/**
 * 
 */
package com.kant.design.oops.problem.chess;

/**
 * @author shaskant
 *
 */
public class Piece {
	private PieceType type;
	private PieceColor color;
	
	

	/**
	 * @param type
	 * @param color
	 */
	public Piece(PieceType type, PieceColor color) {
		super();
		this.type = type;
		this.color = color;
	}

	/**
	 * @return the type
	 */
	public PieceType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(PieceType type) {
		this.type = type;
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

}