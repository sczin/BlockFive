package com.bayviewglen.dayfive;

import java.text.DecimalFormat;

public class DecimalFormatter {

	public static void main(String[] args) {
		
		DecimalFormat formatter = new DecimalFormat("######.#");
		double x = 4544654.33;
		formatter = new DecimalFormat("###;###.000");
		System.out.println(formatter.format(x));
	}

}
