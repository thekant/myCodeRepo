package com.kant.designs.system;

import java.util.HashMap;
import java.util.Map;

/**
 * For URL shortner service
 * 
 * The basic process can be: <br/>
 * Insert: <br/>
 * Hash an input long url into a single integer; <br/>
 * Locate a server on the ring and store the key--longUrl on the server; <br/>
 * Compute the shorten url using base conversion (from 10-base to 62-base) and
 * return it to the user.
 * 
 * Retrieve: <br/>
 * Convert the shorten url back to the key using base conversion (from 62-base
 * to 10-base);<br/>
 * Locate the server containing that key and return the longUrl.
 * 
 * @author shaskant
 *
 */
public class BaseConversion {

	private int base;
	private Map<Integer, Character> mapBase;

	/**
	 * @param base
	 * @param mapBase
	 */
	public BaseConversion(int xbase, Map<Integer, Character> xmapBase) {
		this.base = xbase;
		this.mapBase = xmapBase;
	}

	public void setBase(int xbase) {
		this.base = xbase;
	}

	public void setMapBase(Map<Integer, Character> xmapBase) {
		this.mapBase = xmapBase;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * for base 7
		 */
		Map<Integer, Character> theMap = new HashMap<>();
		theMap.put(0, 'A');
		theMap.put(1, 'B');
		theMap.put(2, 'C');
		theMap.put(3, 'D');
		theMap.put(4, 'E');
		theMap.put(5, 'F');
		theMap.put(6, 'G');
		BaseConversion convertor = new BaseConversion(7, theMap);

		System.out.println(convertor.shorturl(114343, 7, theMap));

	}

	/**
	 * generates 6 letter string based on base operated on id..
	 * 
	 * @return
	 */
	public String shorturl(int id, int base, Map<Integer, Character> map) {
		StringBuilder res = new StringBuilder();
		while (id > 0) {
			int digit = id % base;
			res.append(map.get(digit));
			id /= base;
		}
		while (res.length() < 6)
			res.append('0');
		return res.reverse().toString();
	}

}
