package com.djh.test;

public class Test1 {

	public static void main(String[] args) {
		double k = 5000;
		double n = 5000;
		for(int i=1;i<13*5*2;i++){
			n = n*1.01+k;
		}
		System.out.println(n);
	}

}
