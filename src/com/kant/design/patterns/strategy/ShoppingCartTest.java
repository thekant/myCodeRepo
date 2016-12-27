/**
 * 
 */
package com.kant.design.patterns.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy Pattern is very similar to State Pattern. One of the difference is
 * that Context contains state as instance variable and there can be multiple
 * tasks whose implementation can be dependent on the state whereas in strategy
 * pattern strategy is passed as argument to the method and context object
 * doesn’t have any variable to store it. Strategy pattern is useful when we
 * have multiple algorithms for specific task and we want our application to be
 * flexible to chose any of the algorithm at runtime for specific task
 * 
 * @author shaskant
 *
 */
public class ShoppingCartTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		Item item1 = new Item("1234", 10);
		Item item2 = new Item("5678", 40);

		cart.addItem(item1);
		cart.addItem(item2);

		// pay by paypal
		cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

		// pay by credit card
		cart.pay(new CreditCardStrategy("shashi kant", "1567890123456", "124",
				"12/19"));
	}

}

/**
 * strategy Interface
 * 
 * @author shaskant
 *
 */
interface PaymentStrategy {

	public void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;

	public CreditCardStrategy(String nm, String ccNum, String cvv,
			String expiryDate) {
		this.name = nm;
		this.cardNumber = ccNum;
		this.cvv = cvv;
		this.dateOfExpiry = expiryDate;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid with credit/debit card");
	}

}

class PaypalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;

	public PaypalStrategy(String email, String pwd) {
		this.emailId = email;
		this.password = pwd;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal.");
	}

}

class Item {

	private String upcCode;
	private int price;

	public Item(String upc, int cost) {
		this.upcCode = upc;
		this.price = cost;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public int getPrice() {
		return price;
	}

}

/**
 * Payment strategy have to be defined at the time of checkout. <br/>
 * We could have used composition to create instance variable for strategies but
 * we should avoid that as we want the specific strategy to be applied for a
 * particular task. Same is followed in Collections.sort() and Arrays.sort()
 * method that take comparator as argument.
 * 
 * @author shaskant
 *
 */
class ShoppingCart {

	// List of items
	List<Item> items;

	public ShoppingCart() {
		this.items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public int calculateTotal() {
		int sum = 0;
		for (Item item : items) {
			sum += item.getPrice();
		}
		return sum;
	}

	/**
	 * pass the strategy or payment mode at time of checkout.
	 * 
	 * @param paymentMethod
	 */
	public void pay(PaymentStrategy paymentMethod) {
		int amount = calculateTotal();
		paymentMethod.pay(amount);
	}
}
