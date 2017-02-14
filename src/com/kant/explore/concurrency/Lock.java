/**
 * 
 */
package com.kant.explore.concurrency;

/**
 * A unfair lock
 * 
 * @author shaskant
 *
 */
public class Lock {
	protected boolean isLocked = false;
	protected Thread lockingThread = null;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
		lockingThread = Thread.currentThread();
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		notify();
	}
}