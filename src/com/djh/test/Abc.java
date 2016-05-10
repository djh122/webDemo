package com.djh.test;

import java.util.ArrayList;
import java.util.List;

public class Abc {
	static List<Object> list = new ArrayList<Object>();
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<2000;i++){
			System.out.println(i);
			int[] a = new int[1024*1024];
			Thread.sleep(1000);
			list.add(a);
		}
	}

}
