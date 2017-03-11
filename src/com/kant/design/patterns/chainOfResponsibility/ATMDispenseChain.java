/**
 * 
 */
package com.kant.design.patterns.chainOfResponsibility;

import java.util.Scanner;

/**
 * @author shaskant
 *
 */
public class ATMDispenseChain {
	private DispenseChain c1;

	/**
	 * start from biggest value to smaller.
	 */
	public ATMDispenseChain() {
		// initialize the chain
		this.c1 = new Dollar50Dispenser();
		DispenseChain c2 = new Dollar20Dispenser();
		DispenseChain c3 = new Dollar10Dispenser();
		//DispenseChain nothingDispenser=new NothingDispenser();

		// set the chain of responsibility
		c1.setNext(c2);
		c2.setNext(c3);
		//c3.setNext(nothingDispenser);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ATMDispenseChain atmDispenser = new ATMDispenseChain();
		Scanner input = new Scanner(System.in);
		while (true) {
			int amount = 0;
			System.out.println("Enter amount to dispense");
			amount = input.nextInt();
			if (amount <= 0 || amount % 10 != 0) {
				System.out.println("Amount should be in multiple of 10s.");
				break;
			}
			// process the request
			atmDispenser.c1.dispense(new Currency(amount));
		}
		input.close();
	}

}

/**
 * 
 * @author shaskant
 *
 */
class Currency {
	private int amount;

	public Currency(int amt) {
		this.amount = amt;
	}

	public int getAmount() {
		return this.amount;
	}
}

/**
 * 
 * @author shaskant
 *
 */
abstract class DispenseChain {
	protected DispenseChain successor = null;

	protected DispenseChain getNext() {
		return successor;
	}

	public void setNext(DispenseChain next) {
		this.successor = next;
	}

	abstract void dispense(Currency cur);
}

/**
 * 
 * @author shaskant
 *
 */
class Dollar50Dispenser extends DispenseChain {

	@Override
	public void dispense(Currency cur) {
		if (cur.getAmount() >= 50) {
			int num = cur.getAmount() / 50;
			int remainder = cur.getAmount() % 50;
			System.out.println("Dispensing " + num + " 50$ note");
			if (remainder != 0)
				this.getNext().dispense(new Currency(remainder));
		} else {
			this.getNext().dispense(cur);
		}
	}
}

/**
 * 
 * @author shaskant
 *
 */
class Dollar20Dispenser extends DispenseChain {

	@Override
	public void dispense(Currency cur) {
		if (cur.getAmount() >= 20) {
			int num = cur.getAmount() / 20;
			int remainder = cur.getAmount() % 20;
			System.out.println("Dispensing " + num + " 20$ note");
			if (remainder != 0)
				getNext().dispense(new Currency(remainder));
		} else {
			getNext().dispense(cur);
		}
	}
}

/**
 * 
 * @author shaskant
 *
 */
class Dollar10Dispenser extends DispenseChain {

	@Override
	public void dispense(Currency cur) {
		if (cur.getAmount() >= 10) {
			int num = cur.getAmount() / 10;
			int remainder = cur.getAmount() % 10;
			System.out.println("Dispensing " + num + " 10$ note");
			if (remainder != 0)
				getNext().dispense(new Currency(remainder));
		} else {
			getNext().dispense(cur);
		}
	}
}

class NothingDispenser extends DispenseChain {

	@Override
	void dispense(Currency cur) {
		System.out.println("cannot dispence: " + cur.getAmount()
				+ " is not a multiple of 10");
	}
}