/**
 * 
 */
package com.kant.design.patterns.visitor;

/**
 * Visitor pattern is used when we have to perform an operation on a group of
 * similar kind of Objects. With the help of visitor pattern, we can move the
 * operational logic from the objects to another class.
 * 
 * @author shaskant
 *
 */
public class ShoppingCartClient {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ItemElement[] items = new ItemElement[] { new Book(20, "1234"),
				new Book(100, "5678"), new Fruit(10, 2, "Banana"),
				new Fruit(5, 5, "Apple") };

		int total = calculatePrice(items);
		System.out.println("Total Cost = " + total);
	}

	/**
	 * The benefit of this pattern is that if the logic of operation changes,
	 * then we need to make change only in the visitor implementation rather
	 * than doing it in all the item classes.
	 * 
	 * Another benefit is that adding a new item to the system is easy, it will
	 * require change only in visitor interface and implementation and existing
	 * item classes will not be affected.
	 * 
	 * @param items
	 * @return
	 */
	private static int calculatePrice(ItemElement[] items) {
		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		int sum = 0;
		for (ItemElement item : items) {
			sum = sum + item.accept(visitor);
		}
		return sum;
	}
}

/**
 * 
 * @author shaskant
 *
 */
interface ItemElement {
	public int accept(ShoppingCartVisitor visitor);
}

/**
 * 
 * @author shaskant
 *
 */
class Book implements ItemElement {

	private int price;
	private String isbnNumber;

	public Book(int cost, String isbn) {
		this.price = cost;
		this.isbnNumber = isbn;
	}

	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

/**
 * 
 * @author shaskant
 *
 */
class Fruit implements ItemElement {

	private int pricePerKg;
	private int weight;
	private String name;

	public Fruit(int priceKg, int wt, String nm) {
		this.pricePerKg = priceKg;
		this.weight = wt;
		this.name = nm;
	}

	public int getPricePerKg() {
		return pricePerKg;
	}

	public int getWeight() {
		return weight;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

/**
 * 
 * @author shaskant
 *
 */
interface ShoppingCartVisitor {
	int visit(Book book);

	int visit(Fruit fruit);
}

/**
 * think of a Shopping cart where we can add different type of items (Elements).
 * When we click on checkout button, it calculates the total amount to be paid.
 * Now we can have the calculation logic in item classes or we can move out this
 * logic to another class using visitor pattern. Let’s implement this in our
 * example of visitor pattern.
 * 
 * @author shaskant
 *
 */
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

	/**
	 * apply individual discount etc ..based set of rules
	 */
	@Override
	public int visit(Book book) {

		int cost = 0;
		// apply 5$ discount if book price is greater than 50
		if (book.getPrice() > 50) {
			cost = book.getPrice() - 5;
		} else
			cost = book.getPrice();
		System.out.println("Book ISBN::" + book.getIsbnNumber() + " cost = "
				+ cost);
		return cost;
	}

	@Override
	public int visit(Fruit fruit) {
		int cost = fruit.getPricePerKg() * fruit.getWeight();
		System.out.println(fruit.getName() + " cost = " + cost);
		return cost;
	}

}