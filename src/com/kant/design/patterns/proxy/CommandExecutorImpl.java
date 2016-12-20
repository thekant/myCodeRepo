/**
 * 
 */
package com.kant.design.patterns.proxy;

import java.io.IOException;

/**
 * @author shaskant
 *
 */
public class CommandExecutorImpl implements CommandExecutor {

	@Override
	public void runCommand(String cmd) throws IOException {
                //some heavy implementation
		Runtime.getRuntime().exec(cmd);
		System.out.println("'" + cmd + "' command executed.");
	}

}