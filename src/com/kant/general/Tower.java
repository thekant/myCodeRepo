package com.kant.general;

import java.util.Stack;

/**
 * Tower Of Hanoi problem.
 * 
 * @author shashi
 * 
 */
public class Tower {
	private Stack<Integer> disks;
	private int index; // index of this tower

	public Tower(int index) {
		disks = new Stack<Integer>();
		this.setIndex(index);
	}

	public int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 
	 * @param d
	 */
	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error placing disk " + (d + 1));
			return;
		}
		disks.push(d);
	}

	/**
	 * 
	 * @param t
	 */
	public void moveTopto(Tower t) {
		int top = disks.pop();
		t.add(top);
		System.out.println("Moving disk <d" + (top + 1) + "> from <T"
				+ (getIndex() + 1) + "> to <T" + (t.getIndex() + 1) + ">");
	}

	/**
	 * Contents of this tower.
	 */
	public void contents() {
		System.out.println("Contents of tower " + (getIndex() + 1));
		Integer content = disks.pop();
		if (content == null)
			System.out.println("<empty>");
		while (content != null) {
			System.out.print("<d" + (content + 1) + "> ");
			content = disks.pop();
		}
	}

	/**
	 * n is no. of disks being handled on this tower.
	 */
	public void moveDisks(int n, Tower destination, Tower buffer) {
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveTopto(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 5;
		Tower[] towers = new Tower[n];
		for (int i = 0; i < 3; i++)
			towers[i] = new Tower(i);

		for (int i = n - 1; i >= 0; i--)
			towers[0].add(i);

		towers[0].moveDisks(n, towers[2], towers[1]);
		towers[0].contents();
		towers[1].contents();
		towers[2].contents();

	}

}
