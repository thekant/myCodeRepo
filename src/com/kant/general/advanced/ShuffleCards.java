/**
 * 
 */
package com.kant.general.advanced;

/**
 * @author shashi
 * 
 */
public class ShuffleCards {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] cards = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println("[Before]");
		for (int data : cards) {
			System.out.print(" " + data);
		}
		shuffleTheCards(cards);
		System.out.println("\n[After]");
		for (int data : cards) {
			System.out.print(" " + data);
		}
	}

	/**
	 * Main logic is here.
	 * 
	 * @param cards
	 */
	public static void shuffleTheCards(int[] cards) {
		int index = 0, temp;
		for (int count = 0; count < cards.length; count++) {
			index = (int) (Math.random() * (cards.length - count)) + count;
			// swap with the slot marked by count.
			temp = cards[index];
			cards[index] = cards[count];
			cards[count] = temp;
		}
	}
}
