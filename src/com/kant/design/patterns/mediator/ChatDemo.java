/**
 * 
 */
package com.kant.design.patterns.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaskant
 *
 */
public class ChatDemo {
	public static void main(String[] args) {
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "Shashi");
		User user2 = new UserImpl(mediator, "Lisa");
		User user3 = new UserImpl(mediator, "Saurabh");
		User user4 = new UserImpl(mediator, "David");
		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);

		user1.sendToAll("Hi All");
		user2.send("chal be", user1);

	}
}

/**
 * Channel to communicate
 * 
 * @author shaskant
 *
 */
interface ChatMediator {

	public void sendMessageToAll(String msg, User user);

	public void sendPrivateMessage(String msg, User fromUser, User toUser);

	void addUser(User user);
}

/**
 * Users can send and receive messages, so we can have User interface or
 * abstract class.
 * 
 * @author shaskant
 *
 */
abstract class User {
	protected ChatMediator mediator;
	protected String name;

	public User(ChatMediator med, String name) {
		this.mediator = med;
		this.name = name;
	}

	public abstract void send(String msg, User toUser);

	public abstract void sendToAll(String msg);

	public abstract void receive(String msg);
	
	public String getName() {
		return name;
	}
}

/**
 * Now we will create concrete mediator class, it will have a list of users in
 * the group and provide logic for the communication between the users.
 * 
 * @author shaskant
 *
 */
class ChatMediatorImpl implements ChatMediator {

	private List<User> users;

	public ChatMediatorImpl() {
		this.users = new ArrayList<>();
	}

	@Override
	public void addUser(User user) {
		this.users.add(user);
	}

	/**
	 * bit similar to Observer pattern,but its two way communication
	 */
	@Override
	public void sendMessageToAll(String msg, User user) {
		for (User u : this.users) {
			// message should not be received by the user sending it
			if (u != user) {
				u.receive(msg);
			}
		}
	}

	@Override
	public void sendPrivateMessage(String msg, User fromUser, User toUser) {
		toUser.receive("[" + fromUser.getName() + "]: " + msg);
	}
}

/**
 * 
 * @author shaskant
 *
 */
class UserImpl extends User {

	public UserImpl(ChatMediator med, String name) {
		super(med, name);
	}

	@Override
	public void sendToAll(String msg) {
		System.out.println(this.name + ": Sending Message=" + msg);
		mediator.sendMessageToAll(msg, this);
	}

	@Override
	public void receive(String msg) {
		System.out.println(this.name + ": Received Message:" + msg);
	}

	@Override
	public void send(String msg, User toUser) {
		System.out.println(this.name + ": Sending Private Message to "+toUser.getName()+" =" + msg);
		mediator.sendPrivateMessage(msg, this, toUser);
	}

}