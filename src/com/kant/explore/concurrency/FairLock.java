package com.kant.explore.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * No starvation and every thread is assured to get a chance to execute. NOTE:
 * there is no nested synchronized block here for lock().
 * 
 * In deadlock, two threads are waiting for each other to release locks.
 * 
 * In nested monitor lockout, Thread 1 locks A and B, then releases B and waits
 * for a signal from Thread 2. Thread 2 needs both A and B to send Thread 1 the
 * signal. So, one thread is waiting for a signal, and another for a lock to be
 * released.
 * 
 * @author shaskant
 *
 */
public class FairLock extends Lock {
	private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

	/**
	 * To avoid slipped conditions the testing and setting of the conditions
	 * must be done atomically by the thread doing it, meaning that no other
	 * thread can check the condition in between the testing and setting of the
	 * condition by the first thread.
	 */
	@Override
	public void lock() throws InterruptedException {
		QueueObject queueObject = new QueueObject();
		boolean isLockedForThisThread = true;
		synchronized (this) {
			waitingThreads.add(queueObject);
		}
		while (isLockedForThisThread) {
			// the if part of condition check
			synchronized (this) {
				/**
				 * if lock is not acquired by anyone then check if this is your
				 * turn. <br/>
				 * else if it's locked (isLocked = true) then wait.
				 */
				isLockedForThisThread = isLocked
						|| waitingThreads.get(0) != queueObject;
				if (!isLockedForThisThread) {
					isLocked = true;
					waitingThreads.remove(queueObject);
					lockingThread = Thread.currentThread();
					return;// lock acquired so return.
				}
			}
			// wait if it's not your turn.
			try {
				queueObject.doWait();
			} catch (InterruptedException e) {
				synchronized (this) {
					waitingThreads.remove(queueObject);
				}
				throw e;
			}
		}
	}
	
	@Override
	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}

	/**
	 * some issues [slipped condition at line 78] <br/>
	 * http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html
	 * 
	 * @throws InterruptedException
	 */
	public void alterNateLock() throws InterruptedException {
		QueueObject queueObject = new QueueObject();
		boolean isLockedForThisThread = true;
		synchronized (this) {
			waitingThreads.add(queueObject);
		}
		while (isLockedForThisThread) {
			// if it's already locked then wait.
			if (isLocked) { // slipped condition even if synchronized
				try {
					queueObject.doWait();
				} catch (InterruptedException e) {
					synchronized (this) {
						waitingThreads.remove(queueObject);
					}
					throw e;
				}
			}
			// if not locked then check if it's your turn.
			else {
				synchronized (this) {
					if (waitingThreads.get(0) != queueObject) {
						isLocked = true;
						waitingThreads.remove(queueObject);
						lockingThread = Thread.currentThread();
						return;// lock acquired so return.
					}
				}
			}
		}
	}

}

/**
 * Handles missed signals.
 * 
 * @author shaskant
 *
 */
class QueueObject {
	private boolean isNotified = false;

	public synchronized void doWait() throws InterruptedException {
		while (!isNotified) {
			this.wait();
		}
		this.isNotified = false;
	}

	public synchronized void doNotify() {
		this.isNotified = true;
		this.notify();
	}

	public boolean equals(Object o) {
		return this == o;
	}
}