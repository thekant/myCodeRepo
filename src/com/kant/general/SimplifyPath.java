/**
 * 
 */
package com.kant.general;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * https://leetcode.com/problems/simplify-path/?tab=Description
 * 
 * For example,
 * 
 * path = "/home/", => "/home"
 * 
 * path = "/a/./b/../../c/", => "/c"
 * 
 * @author shaskant
 *
 */
public class SimplifyPath {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * The main idea is to push to the stack every valid file name (not in
	 * {"",".",".."}), popping only if there's something to pop and we met "..".
	 * 
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		Deque<String> stack = new LinkedList<>();
		Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
		for (String dir : path.split("/")) {
			if (dir.equals("..") && !stack.isEmpty())
				stack.pop();
			else if (!skip.contains(dir))
				stack.push(dir);
		}
		String res = "";
		for (String dir : stack)
			res = "/" + dir + res;
		return res.isEmpty() ? "/" : res;
	}
}
