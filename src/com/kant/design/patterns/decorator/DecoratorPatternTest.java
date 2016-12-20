/**
 * 
 */
package com.kant.design.patterns.decorator;

/**
 * @author shaskant
 *
 */
public class DecoratorPatternTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Car sportsCar = new SportsCar(new BasicCar());
		sportsCar.assemble();
		System.out.println("\n*****");

		Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
		sportsLuxuryCar.assemble();
	}

}

/**
 * Component Interface – The interface or abstract class defining the methods
 * that will be implemented. In our case Car will be the component interface.
 * 
 * @author shaskant
 *
 */
interface Car {
	public void assemble();
}

/**
 * Component Implementation – The basic implementation of the component
 * interface. We can have BasicCar class as our component implementation.
 * 
 * @author shaskant
 *
 */
class BasicCar implements Car {

	@Override
	public void assemble() {
		System.out.print("Basic Car.");
	}

}

/**
 * Decorator – Decorator class implements the component interface and it has a
 * HAS-A relationship with the component interface. The component variable
 * should be accessible to the child decorator classes, so we will make this
 * variable protected.
 * 
 * @author shaskant
 *
 */
class CarDecorator implements Car {

	protected Car car;

	public CarDecorator(Car c) {
		this.car = c;
	}

	@Override
	public void assemble() {
		this.car.assemble();
	}

}

/**
 * Concrete Decorators – Extending the base decorator functionality and
 * modifying the component behavior accordingly. We can have concrete decorator
 * classes as LuxuryCar and SportsCar
 * 
 * @author shaskant
 *
 */
class SportsCar extends CarDecorator {

	public SportsCar(Car c) {
		super(c);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Sports Car.");
	}
}

class LuxuryCar extends CarDecorator {

	public LuxuryCar(Car c) {
		super(c);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Luxury Car.");
	}
}
