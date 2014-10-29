package com.bayviewglen.strings;

import java.util.Scanner;

public class One {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter your name");
		String name = input.nextLine();
		System.out.println("Hello " + name + "!");
		input.close();
	}

}
