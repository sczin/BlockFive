package com.bayviewglen.strings;

import java.util.Scanner;

public class Three {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the word");
		String word = input.nextLine();
		System.out.println("enter the tag");
		String tags = input.nextLine();
		
		System.out.println("<" + tags + ">"+ word + "<" + tags.charAt(0) + "/" + tags.substring(1) + ">");
	input.close();
	}

}
