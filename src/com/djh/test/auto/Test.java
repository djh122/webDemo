package com.djh.test.auto;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {

	static Random random = new Random();
	public static int test() {
		int N = 1000;
		int S = 100;
		List<Bean> beans = new LinkedList<Test.Bean>();
		for(int i=0;i<N;i++){
			beans.add(new Bean(i,S));
		}
		
		return 0;
	}
	public static void stat() {
		int count = 0;
		for(int i=0;i<1000;i++){
			count += test();
		}
		System.out.println(count/1000);
	}
	static class Bean{
		int index;
		double amount;
		double comsume = 1;
		double ist = 0.05;
		public Bean(int index,int amount){
			this.index = index;
			this.amount = amount;
			//this.comsume = comsume;
		}
		
		public void increase() {
			amount = amount*(1+ist);
		}
		public void comsume(double x) {
			amount = amount - x*comsume;
		}
	
		@Override
		public String toString() {
			return "人员编号："+index+",金额："+amount;
		}
	}
	public static void main(String[] args) {
		//test();
		//stat();
		System.out.println(random.nextGaussian());
	}
}
