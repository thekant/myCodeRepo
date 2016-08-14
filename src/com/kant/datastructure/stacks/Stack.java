/**
 * 
 */
package com.kant.datastructure.stacks;

/**
 * Applications of stack: <br/>
 * Balancing of symbols: Infix to Postfix/Prefix conversion <br/>
 * Redo-undo features at many places like editors, photoshop. <br/>
 * Forward and backward feature in web browsers <br/>
 * Used in many algorithms like Tower of Hanoi, tree traversals, stock span
 * problem, histogram problem. <br/>
 * Other applications can be Backtracking, Knight tour problem, rat in a maze, N
 * queen problem and sudoku solver<br/>
 * 
 * <br/>
 * 1. http://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-
 * expression/
 * 
 * @author shaskant
 *
 */
public interface Stack<T> {
	boolean isEmpty();

	T peek();

	T pop();

	void push(T data);
}
