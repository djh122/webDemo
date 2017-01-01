package com.djh.test;

import java.math.BigInteger;
import java.util.Scanner;
public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = Integer.valueOf(scanner.nextLine());
		for(int i=1;i<=k;i++){
			String line = scanner.nextLine();
			String[] ns = line.split("\\s+");
			BigInteger a = new BigInteger(ns[0]);
			BigInteger b = new BigInteger(ns[1]);
			System.out.println("Case "+i+":");
			System.out.println(a+" + "+b+" = "+(a.add(b)));
			System.out.println();
		}
			
	}
}