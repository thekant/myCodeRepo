/**
 * 
 */
package com.kant.design.patterns.adaptor;

/**
 * Intent:
 * 
 * Convert the interface of a class into another interface clients expect.
 * Adapter lets classes work together that couldn't otherwise because of
 * incompatible interfaces. Wrap an existing class with a new interface.
 * Impedance match an old component to a new system.
 * 
 * 
 * The Adapter pattern allows otherwise incompatible classes to work together by
 * converting the interface of one class into an interface expected by the
 * clients.
 * 
 * @author shaskant
 *
 */
public class AdapterDemo {
	public static void main(String[] args) {
		Shape[] shapes = { new Line(), new Rectangle() };
		// A begin and end point from a graphical editor
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		for (int i = 0; i < shapes.length; ++i)
			shapes[i].draw(x1, y1, x2, y2);
	}
}

/**
 * old class to draw line [adaptee]
 *
 * @author shaskant
 */
class LegacyLine {
	public void draw(int x1, int y1, int x2, int y2) {
		System.out.println("line from (" + x1 + ',' + y1 + ") to (" + x2 + ','
				+ y2 + ')');
	}
}

/**
 * old class to draw rectangle [adaptee]
 * 
 * @author shaskant
 *
 */
class LegacyRectangle {
	public void draw(int x, int y, int w, int h) {
		System.out.println("rectangle at (" + x + ',' + y + ") with width " + w
				+ " and height " + h);
	}
}

/**
 * new interface that clients uses
 * 
 * @author shaskant
 *
 */
interface Shape {
	void draw(int x1, int y1, int x2, int y2);
}

/**
 * New class based on new interface [Adaptor for LegacyLine]
 * 
 * @author shaskant
 *
 */
class Line implements Shape {
	private LegacyLine adaptee = new LegacyLine();

	public void draw(int x1, int y1, int x2, int y2) {
		adaptee.draw(x1, y1, x2, y2);
	}
}

/**
 * [Adaptor for LegacyRectangle]
 * 
 * @author shaskant
 *
 */
class Rectangle implements Shape {
	private LegacyRectangle adaptee = new LegacyRectangle();

	public void draw(int x1, int y1, int x2, int y2) {
		adaptee.draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1),
				Math.abs(y2 - y1));
	}
}
