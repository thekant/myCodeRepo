/**
 * 
 */
package com.kant.explore.concurrency;

/**
 * @author shaskant
 *
 */
public class ReenterantLock extends Lock {
	protected int lockCount = 0;

	@Override
	public synchronized void lock() throws InterruptedException {
		while (isLocked && Thread.currentThread() != lockingThread) {
			wait();
		}
		isLocked = true;
		lockCount++;
		lockingThread = Thread.currentThread();
	}

	@Override
	public synchronized void unlock() {
		if (lockingThread == Thread.currentThread()) {
			lockCount--;

			if (lockCount == 0) {
				isLocked = false;
				lockingThread = null;
				notify();
			}
		} else
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
	}
}
