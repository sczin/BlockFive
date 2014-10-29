package com.bayviewglen.random;

import java.util.Scanner;

public class Homework {

	public static void main(String[] args) {
		int a = 100;
		int b = 1;
		int c = (int)(Math.random()*a)+b;
		System.out.println(c);
		
		int d = 101;
		int e = -50;
		int f = (int)(Math.random()*d)+e;
		System.out.println(f);
		
		Scanner input = new Scanner(System.in);
		System.out.println("enter a number");
		int g = input.nextInt();
		System.out.println("enter another number");
		int h = input.nextInt();
		int dif = g-h;
		int i = (int)(Math.random()*dif)+h;
		System.out.println(i);
		
		int j = 6;
		int k = 1;
		int l = (int)(Math.random()*j)+k;
		System.out.println("you rolled: " + l);		
		
		
		System.out.println("enter a word");
		String m = input.next();
		char n = m.charAt((int)(Math.random()*m.length()));
		System.out.println(n);
		
		int o = 26;
		int p = 65;
		char q = (char)((int)(Math.random()*o)+p);
		System.out.println(q);
		
		int r = 97;
		char s = (char)((int)(Math.random()*o)+r);
		System.out.println(s);
		input.close();
			}

}
