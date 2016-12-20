/**
 * 
 */
package com.kant.design.patterns.mediator;

/**
 * https://sourcemaking.com/design_patterns/behavioral_patterns
 * 
 * http://www.journaldev.com/1730/mediator-design-pattern-java
 * 
 * @author shaskant
 *
 */
// 1. The "intermediary"
class Mediator {
	// 4. The Mediator arbitrates
	private boolean slotFull = false;
	private int number;

	public synchronized void storeMessage(int num) {
		// no room for another message
		while (slotFull == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		slotFull = true;
		number = num;
		notifyAll();
	}

	public synchronized int retrieveMessage() {
		// no message to retrieve
		while (slotFull == false)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = false;
		notifyAll();
		return number;
	}
}

class Producer extends Thread {
	// 2. Producers are coupled only to the Mediator
	private Mediator med;
	private int id;
	private static int num = 1;

	public Producer(Mediator m) {
		med = m;
		id = num++;
	}

	public void run() {
		int num;
		while (true) {
			med.storeMessage(num = (int) (Math.random() * 100));
			System.out.print("p" + id + "-" + num + "  ");
		}
	}
}

class Consumer extends Thread {
	// 3. Consumers are coupled only to the Mediator
	private Mediator med;
	private int id;
	private static int num = 1;

	public Consumer(Mediator m) {
		med = m;
		id = num++;
	}

	public void run() {
		while (true) {
			System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
		}
	}
}

public class MediatorDemo {
	public static void main(String[] args) {
		Mediator mb = new Mediator();
		new Producer(mb).start();
		new Producer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
	}
}