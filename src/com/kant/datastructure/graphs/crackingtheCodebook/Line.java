package com.kant.datastructure.graphs.crackingtheCodebook;

/**
 * 
 * @author shaskant
 *
 */
public class Line {
	private double slope;
	private double intercept;
	private boolean infinite_slope = false;
	public static final double epsilon = 0.0001;

	/**
	 * 
	 * @param p1
	 * @param p2
	 */
	public Line(GraphPoint p1, GraphPoint p2) {
		// normal slope case
		if (Math.abs(p2.x - p1.x) > epsilon) {
			setSlope((p2.y - p1.y) / (p2.x - p1.y));
			setIntercept(p1.y - getSlope() * p1.x);
		} else {// infinite slope
			infinite_slope = true;
			setIntercept(p2.x);// or p1.x
			setSlope(0);
		}
	}

	@Override
	public int hashCode() {
		int sl = (int) (getSlope() * 1000);
		int intrp = (int) (getIntercept() * 1000);
		return sl | intrp;
	}

	/**
	 * utility to calculate equality of two double values
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean isEqual(double a, double b) {
		return (Math.abs(a - b) < epsilon);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false; // base check

		// deep check
		Line other = (Line) obj;
		if (isEqual(getIntercept(), other.getIntercept())
				&& isEqual(getSlope(), other.getSlope())
				&& (infinite_slope == other.infinite_slope))
			return true;
		return false;
	}

	/**
	 * @return the intercept
	 */
	public double getIntercept() {
		return intercept;
	}

	/**
	 * @param intercept
	 *            the intercept to set
	 */
	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	/**
	 * @return the slope
	 */
	public double getSlope() {
		return slope;
	}

	/**
	 * @param slope
	 *            the slope to set
	 */
	public void setSlope(double slope) {
		this.slope = slope;
	}

}
