/**
 * 
 */
package com.kant.design.patterns.bridge;

/**
 * Intent:
 * 
 * Decouple an abstraction from its implementation so that the two can vary
 * independently. Publish interface in an inheritance hierarchy, and bury
 * implementation in its own inheritance hierarchy. Beyond encapsulation, to
 * insulation
 * 
 * https://sourcemaking.com/design_patterns/bridge
 * 
 * The Bridge design pattern proposes refactoring the exponentially explosive
 * inheritance hierarchy into two orthogonal hierarchies – one for
 * platform-independent abstractions, and the other for platform-dependent
 * implementations.
 * <p>
 * <b>Example:</b><br/>
 * The Bridge pattern decouples an abstraction from its implementation, so that
 * the two can vary independently. A household switch controlling lights,
 * ceiling fans, etc. is an example of the Bridge. The purpose of the switch is
 * to turn a device on or off. The actual switch can be implemented as a pull
 * chain, simple two position switch, or a variety of dimmer switches.
 * </p>
 * 
 * 
 * Check list<br/>
 * -----------<br/>
 * 
 * Decide if two orthogonal dimensions exist in the domain. These independent
 * concepts could be: abstraction/platform, or domain/infrastructure, or
 * front-end/back-end, or interface/implementation.
 * 
 * Design the separation of concerns: what does the client want, and what do the
 * platforms provide.
 * 
 * Design a platform-oriented interface that is minimal, necessary, and
 * sufficient. Its goal is to decouple the abstraction from the platform. Define
 * a derived class of that interface for each platform.
 * 
 * Create the abstraction base class that "has a" platform object and delegates
 * the platform-oriented functionality to it. Define specializations of the
 * abstraction class if desired.
 * 
 * 
 * @author shaskant
 *
 */
public class BridgePatternTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Shape tri = new Triangle(new RedColor());
		tri.applyColor();

		Shape pent = new Pentagon(new GreenColor());
		pent.applyColor();
	}

}
