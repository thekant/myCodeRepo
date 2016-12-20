/**
 * 
 */
package com.kant.design.patterns.proxy;

/**
 * @author shaskant
 *
 */
public class ProxyPatternTest {

	/**
	 * NOTE: commands work only on linux env
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CommandExecutor executor = new CommandExecutorProxy("shashi",
				"wrong_password");
		try {
			executor.runCommand("ls -ltr");
			executor.runCommand(" rm -rf abc.pdf");
		} catch (Exception e) {
			System.out.println("Exception Message::" + e.getMessage());
		}

	}

}
