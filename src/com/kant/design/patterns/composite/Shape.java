/**
 * 
 */
package com.kant.design.patterns.composite;

/**
 * Composite design pattern leaf implements base component and these are the
 * building block for the composite. We can create multiple leaf objects such as
 * Triangle, Circle etc.
 * 
 * {@link Shape} is base component
 * 
 * @author shaskant
 *
 */
public interface Shape {

	public void draw(String fillColor);
}
