package com.kant.datastructure.graphs.crackingtheCodebook;

import java.util.HashMap;

/**
 * Given a two dimensional graph with points on it, find a line which passes the
 * most number of points.
 * 
 * @author shaskant
 *
 */
public class LineThatPassesMaximumPoints {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a graph[] and then call findbestLine()
		GraphPoint[] gpoints = { new GraphPoint(0, 0), new GraphPoint(1, 1),
				new GraphPoint(3, 3), new GraphPoint(5, 4),
				new GraphPoint(4, 5), new GraphPoint(5, 5) };
		Line line = findBestLine(gpoints);
		System.out.println(line.getIntercept() + "," + line.getSlope());

	}

	public static Line findBestLine(GraphPoint[] gpoints) {
		Line bestLine = null;
		HashMap<Line, Integer> hashmap = new HashMap<Line, Integer>();
		for (int i = 0; i < gpoints.length; i++) {
			for (int j = i + 1; j < gpoints.length; j++) {
				Line line = new Line(gpoints[i], gpoints[j]);
				if (!hashmap.containsKey(line)) {
					hashmap.put(line, 0);
				} else
					hashmap.put(line, hashmap.get(line) + 1); // replacing old
																// value
				if (bestLine != null) {
					if (hashmap.get(line) > hashmap.get(bestLine))
						bestLine = line;
				} else
					bestLine = line;
			}
		}
		return bestLine;
	}

}
