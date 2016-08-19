/**
 * 
 */
package com.kant.datastructure.queues;

/**
 * @author shaskant
 *
 */
public class OverFlowException extends Exception {

	/**
	 * 
	 */
	public OverFlowException() {
	}

	/**
	 * @param message
	 */
	public OverFlowException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OverFlowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OverFlowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OverFlowException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
