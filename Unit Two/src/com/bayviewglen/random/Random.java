package com.bayviewglen.random;

import java.util.Scanner;

public class Random {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter a word");
		String s = input.nextLine();
		char c = s.charAt((int)(Math.random()*s.length()));
		System.out.println(c);
		input.close();
	}

}
