package com.bayviewglen.strings;

import java.util.Scanner;

public class Two {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter something");
		String a = input.nextLine();
		System.out.println("enter another something");
		String b = input.nextLine();
		System.out.println(a + b + b + a);
input.close();
	}

}
