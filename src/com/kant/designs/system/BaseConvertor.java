package com.kant.designs.system;

/**
 * For URL Shortner service
 * 
 * The basic process can be: <br/>
 * <b>Insert:</b> <br/>
 * Hash an input long url into a single integer; <br/>
 * Locate a server on the ring and store the key--longUrl on the server; <br/>
 * Compute the shorten url using base conversion (from 10-base to 62-base) and
 * return it to the user.
 * 
 * <b> Retrieve:</b> <br/>
 * Convert the shorten url back to the key using base conversion (from 62-base
 * to 10-base);<br/>
 * Locate the server containing that key and return the longUrl.
 * 
 * @author shaskant
 *
 */
public class BaseConvertor {

	private int base;
	private char[] mapBase;

	/**
	 * @param base
	 * @param mapBase
	 */
	public BaseConvertor(int xbase, char[] xmapBase) {
		this.base = xbase;
		this.mapBase = xmapBase;
	}

	public void setBase(int xbase) {
		this.base = xbase;
	}

	public void setMapBase(char[] xmapBase) {
		this.mapBase = xmapBase;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * for Base 62 conversion
		 */
		char[] theMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		BaseConvertor convertor = new BaseConvertor(theMap.length, theMap);

		String url = "http://google.com";

		/**
		 * hash long url string into a number and convert number to short url.
		 */
		System.out.println(convertor.shorturl(Math.abs(url.hashCode())));
	}

	/**
	 * generates 6 letter string based on base operated on id..
	 * 
	 * @return
	 */
	public String shorturl(int id) {
		StringBuilder res = new StringBuilder();
		while (id > 0) {
			int digit = id % base;
			res.append(mapBase[digit]);
			id /= base;
		}
		while (res.length() < 6)
			res.append('0');
		return res.reverse().toString();
	}
}
