package com.bayviewglen.decrypt;

public class one {
static final int SHIFTER = 102;
	public static void main(String[] args) {
		int characterAt = 0;
		String s = "ymjwjnxstbbfdnhfsljyymnxbwts";
		int stringLength = s.length();
		boolean done = false;
		String r;
		
		while(!done){
			char letter = s.charAt(characterAt);
			r = "" + (char)((letter-5));
			System.out.print(r);
			characterAt++;
		if(characterAt == stringLength){
				done = true;
				
				//i know what i would need to do (ASCII)
			}
		}
	}
}

