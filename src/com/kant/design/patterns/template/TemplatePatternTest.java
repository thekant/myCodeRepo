/**
 * 
 */
package com.kant.design.patterns.template;

/**
 * Template Method is a behavioral design pattern. Template Method design
 * pattern is used to create a method stub and deferring some of the steps of
 * implementation to the subclasses.
 * 
 * @author shaskant
 *
 */
public class TemplatePatternTest {
	public static void main(String[] args) {
		HouseTemplate houseType = new WoodenHouse();

		// using template method
		houseType.buildHouse();
		System.out.println("************");

		houseType = new GlassHouse();

		houseType.buildHouse();
	}
}

abstract class HouseTemplate {

	/**
	 * final so subclasses can't override.<br/>
	 * buildHouse() is the template method and defines the order of execution
	 * for performing several steps.
	 */
	public final void buildHouse() {
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built.");
	}

	// default implementation
	private void buildWindows() {
		System.out.println("Building Glass Windows");
	}

	// methods to be implemented by subclasses
	public abstract void buildWalls();

	public abstract void buildPillars();

	private void buildFoundation() {
		System.out
				.println("Building foundation with cement,iron rods and sand");
	}
}

class WoodenHouse extends HouseTemplate {

	@Override
	public void buildWalls() {
		System.out.println("Building Wooden Walls");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Wood coating");
	}

}

class GlassHouse extends HouseTemplate {

	@Override
	public void buildWalls() {
		System.out.println("Building Glass Walls");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with glass coating");
	}

}
