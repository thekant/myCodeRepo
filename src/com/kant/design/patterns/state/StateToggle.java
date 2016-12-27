package com.kant.design.patterns.state;

/**
 * 
 * @author shaskant
 *
 */
import java.io.*;

class Button {
	// stores context
	private State current;

	public Button() {
		current = OFF.instance();
	}

	public void setCurrent(State s) {
		current = s;
	}

	public void push() {
		current.push(this);
	}
}

// 4. The "wrappee" hierarchy
abstract class State {
	// 5. Default behavior can go in the base class
	public abstract void push(Button b);
}

class ON extends State {
	private static ON inst = new ON();

	private ON() {
	}

	public static State instance() {
		return inst;
	}

	/**
	 * Current state is ON , so set state to OFF.
	 */
	public void push(Button b) {
		b.setCurrent(OFF.instance());
		System.out.println("   turning OFF");
	}
}

class OFF extends State {
	private static OFF inst = new OFF();

	private OFF() {
	}

	public static State instance() {
		return inst;
	}

	/**
	 * Current state is OFF , so set state to ON.
	 */
	public void push(Button b) {
		b.setCurrent(ON.instance());
		System.out.println("   turning ON");
	}
}

/**
 * 
 * @author shaskant
 *
 */
public class StateToggle {
	public static void main(String[] args) throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		int ch;
		Button btn = new Button();
		while (true) {
			System.out.print("Press 'Enter'");
			ch = is.read();
			ch = is.read();
			btn.push();
		}
	}
}
