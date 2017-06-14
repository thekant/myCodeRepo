/**
 * 
 */
package com.kant.design.oops.problem.chess;

/**
 * @author shaskant
 *
 */
public class Move {

    private Spot from ;
    private Spot to;
    
    /**
	 * @param from
	 * @param to
	 */
	public Move(Spot from, Spot to) {
		super();
		this.from = from;
		this.to = to;
	}

	//TODO
	public Piece getPieceMoved() {
		return null;
    	
    }

	//TODO
    public Piece getPieceCaptured(){
		return null;
    	
    }

    public PieceType getPromotion(){
		return null;
    }

	/**
	 * @return the from
	 */
	public Spot getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Spot from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Spot getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Spot to) {
		this.to = to;
	}
}