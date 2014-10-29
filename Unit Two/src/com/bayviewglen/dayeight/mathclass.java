/*
 * math class
 */
package com.bayviewglen.dayeight;

public class mathclass {

	public static void main(String[] args) {
		int x = 2;
		int y = -2;
		double z = -2.0;
		
		System.out.println(Math.PI*x); 
		System.out.println(Math.pow(x, y));  //pow casts as double
		
		System.out.println(Math.sqrt(Math.abs(y))); // 
		System.out.println(Math.abs(z));
	

		System.out.println(Math.sqrt(x)); // sqrt casts as double
		System.out.println(Math.abs(y));

		// System.out.println(Math.sqrt("shon")); //this is a compile issue, no sqrt method/function accepts Strings as arguments/parameters

		System.out.println(Math.ceil(Math.PI));  //rounds up
		System.out.println(Math.floor(Math.PI)); //rounds down
		System.out.println(Math.round(Math.PI)); //rounds 
	}

}
