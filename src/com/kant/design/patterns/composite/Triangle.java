package com.kant.design.patterns.composite;

/**
 * 
 * @author shaskant
 *
 */
public class Triangle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing Triangle with color " + fillColor);
	}

}
