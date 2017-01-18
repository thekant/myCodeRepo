/**
 * 
 */
package com.kant.system.design.elevators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Elevator in this program implements following features - <br/>
 * If elevator is going up or down, it checks for nearest floor request to
 * process first in that direction. <br/>
 * If there is no request to process, it waits at last processed floor. <br/>
 * If a new request comes while elevator is processing a request. It process the
 * new request first if it is nearest than the processing floor in same
 * direction.
 * 
 * @author shaskant
 *
 */
public class ElevatorControlSystem {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to MyLift");
		// RequestListenerThread to read requested floor and add to Set
		Thread requestListenerThread = new Thread(new RequestListener(),
				"RequestListenerThread");
		// RequestProcessorThread to read Set and process requested floor
		Thread requestProcessorThread = new Thread(new RequestProcessor(),
				"RequestProcessorThread");

		Elevator.getInstance()
				.setRequestProcessorThread(requestProcessorThread);

		requestListenerThread.start();
		requestProcessorThread.start();
	}
}

/**
 * 
 * @author shaskant
 *
 */
class Elevator {
	private static Elevator elevator = null;
	// will be accessed from multiple threads ,synchronize it
	private TreeSet<Integer> requestSet = new TreeSet<Integer>();
	private int currentFloor = 0;
	private Direction direction = Direction.ELEVATOR_UP;

	private Elevator() {
	}

	private Thread requestProcessorThread = null;

	/**
	 * make singleton
	 */
	public static Elevator getInstance() {
		if (elevator == null) {
			synchronized (Elevator.class) {
				if (elevator == null) {
					elevator = new Elevator();
				}
			}
		}
		return elevator;
	}

	public Direction getDirection() {
		return direction;
	}

	/**
	 * cannot directly set direction of elevator from outside of this class
	 * 
	 * @param direction
	 */
	private void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Thread getRequestProcessorThread() {
		return requestProcessorThread;
	}

	public void setRequestProcessorThread(Thread requestProcessorThread) {
		this.requestProcessorThread = requestProcessorThread;
	}

	public TreeSet<Integer> getRequestSet() {
		return requestSet;
	}

	public void setRequestSet(TreeSet<Integer> requestSet) {
		this.requestSet = requestSet;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Only {@link RequestProcessor} can set the current floor and hence decides
	 * the direction to move to.
	 * 
	 * @param currentfloor
	 * @throws InterruptedException
	 */
	public void setCurrentFloor(int currentfloor) throws InterruptedException {
		if (this.currentFloor > currentfloor) {
			setDirection(Direction.ELEVATOR_DOWN);
		} else {
			setDirection(Direction.ELEVATOR_UP);
		}
		this.currentFloor = currentfloor;

		System.out.println("Floor : " + currentFloor);
		Thread.sleep(3000);
	}

	/**
	 * @return next request to process based on elevator current floor and
	 *         direction
	 */
	public synchronized int nextFloor() {
		Integer floor = null;

		if (direction == Direction.ELEVATOR_UP) {
			if (requestSet.ceiling(currentFloor) != null) {
				// set to next greater floor closest to current floor
				floor = requestSet.ceiling(currentFloor);
			} else {
				// else based on request choose closest , can set lift to
				// downward direction
				floor = requestSet.floor(currentFloor);
			}
		} else {
			if (requestSet.floor(currentFloor) != null) {
				floor = requestSet.floor(currentFloor);
			} else {
				// else based on request choose closest , can set lift to upward
				// direction
				floor = requestSet.ceiling(currentFloor);
			}
		}

		if (floor == null) {
			try {
				System.out.println("Waiting at Floor :" + getCurrentFloor());
				wait();// only a new request can notify RequestProcessorThread.
			} catch (InterruptedException e) {
				// in case some issue with elevator system .. print stack trace
				// and return -1.
				e.printStackTrace();
			}
		} else {
			// Remove the request from Set as it is the request in Progress.
			requestSet.remove(floor);
		}
		return (floor == null) ? -1 : floor;
	}

	/**
	 * 
	 * @param f
	 */
	public synchronized void addFloor(int f) {
		requestSet.add(f);

		if (requestProcessorThread.getState() == Thread.State.WAITING) {
			// Notify processor thread that a new request has come if it is
			// waiting
			notify();
		} else {
			// Interrupt Processor thread to check if new request should be
			// processed before current request or not.
			requestProcessorThread.interrupt();
		}
	}

	/**
	 * 
	 * @return
	 */
	public ElevatorStatus status() {
		return (requestSet.size() > 0) ? ElevatorStatus.ELEVATOR_OCCUPIED
				: ElevatorStatus.ELEVATOR_EMPTY;
	}

}

enum Direction {
	ELEVATOR_UP, ELEVATOR_DOWN
}

enum ElevatorStatus {
	ELEVATOR_OCCUPIED, ELEVATOR_EMPTY;
}

/**
 * 
 * @author shaskant
 *
 */
class RequestProcessor implements Runnable {

	/**
	 * running job
	 */
	@Override
	public void run() {
		Elevator elevator = Elevator.getInstance();
		// serve always
		while (true) {
			// get next floor to go to .. will make this thread wait if no
			// requests are there no process
			int floor = elevator.nextFloor();
			// get current floor
			int currentFloor = elevator.getCurrentFloor();
			try {
				// only process if next floor value > 0
				if (floor >= 0) {
					if (currentFloor > floor) {
						// reach down to that floor
						while (currentFloor > floor) {
							elevator.setCurrentFloor(--currentFloor);
						}
					} else {
						// reach up to that floor
						while (currentFloor < floor) {
							elevator.setCurrentFloor(++currentFloor);
						}
					}
					System.out.println("Reached Floor : "
							+ elevator.getCurrentFloor());
				}
			} catch (InterruptedException e) {
				// If a new request has interrupted a current request processing
				// then check
				// >if the current request is already processed fine.
				// >otherwise add it back in request Set again
				if (elevator.getCurrentFloor() != floor) {
					elevator.getRequestSet().add(floor);
				}
			}
		}
	}
}

/**
 * 
 * @author shaskant
 *
 */
class RequestListener implements Runnable {

	@Override
	public void run() {

		while (true) {
			String floorNumberStr = null;
			try {
				// Read input from console
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(System.in));
				floorNumberStr = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (isValidFloorNumber(floorNumberStr)) {
				System.out.println("User Pressed : " + floorNumberStr);
				Elevator elevator = Elevator.getInstance();
				elevator.addFloor(Integer.parseInt(floorNumberStr));
			} else {
				System.out.println("Floor Request Invalid : " + floorNumberStr);
			}
		}
	}

	/**
	 * This method is used to define maximum floors this elevator can process.
	 * 
	 * @param s
	 *            - requested floor
	 * @return true if requested floor is integer and upto two digits. (max
	 *         floor = 99)
	 */
	private boolean isValidFloorNumber(String s) {
		return (s != null) && s.matches("\\d{1,2}");
	}

}