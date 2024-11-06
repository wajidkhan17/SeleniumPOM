package com.qa.opencart.base;

import java.util.Arrays;

public class ArrayNew {

	public static void main(String[] args) {

		Integer[] a = { 0, 1, 9, 8, 0, 2 };
		Integer[] b = { 0, 1, 9, 8, 0, 2, 0, 0 };

		int ind = 0;

		for (Integer c : b) {
			if (c == 0) {
				b[ind] = c;
				ind++;
			}
		}
		
		
		System.out.println(Arrays.toString(b));

		int index = 0;

		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				a[index] = a[i];
				index++;
			}
		}

		while (index < a.length) {
			a[index] = 0;
			index++;
		}

		System.out.println(Arrays.toString(a));
	}

}
