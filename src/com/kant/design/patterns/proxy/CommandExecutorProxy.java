/**
 * 
 */
package com.kant.design.patterns.proxy;

/**
 * Proxy design pattern intent according to GoF is:
 * 
 * Provide a surrogate or placeholder for another object to control access to
 * it.
 * 
 * The definition itself is very clear and proxy design pattern is used when we
 * want to provide controlled access of a functionality.
 * 
 * Let’s say we have a class that can run some command on the system. Now if we
 * are using it, its fine but if we want to give this program to a client
 * application, it can have severe issues because client program can issue
 * command to delete some system files or change some settings that you don’t
 * want.
 * 
 * @author shaskant
 *
 */
public class CommandExecutorProxy implements CommandExecutor {

	private boolean isAdmin;
	private CommandExecutor executor;

	public CommandExecutorProxy(String user, String pwd) {
		if ("shashi".equals(user) && "s#ik@nt".equals(pwd))
			isAdmin = true;
		executor = new CommandExecutorImpl();
	}

	/**
	 * 
	 */
	@Override
	public void runCommand(String cmd) throws Exception {
		if (isAdmin) {
			executor.runCommand(cmd);
		} else {
			if (cmd.trim().startsWith("rm")) {
				throw new Exception(
						"rm command is not allowed for non-admin users.");
			} else {
				executor.runCommand(cmd);
			}
		}
	}

}