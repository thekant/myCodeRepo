/**
 * 
 */
package com.kant.design.patterns.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author shaskant
 *
 */
public interface Shape {

	public void draw(Graphics g, int x, int y, int width, int height,
			Color color);
}
