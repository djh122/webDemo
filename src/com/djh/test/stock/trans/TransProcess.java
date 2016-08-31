package com.djh.test.stock.trans;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class TransProcess {

	static PriorityBlockingQueue<Double> sell = new PriorityBlockingQueue<Double>(10000, new Comparator<Double>() {
		@Override
		public int compare(Double o1, Double o2) {
			if(o1>o2){
				return 1;
			}else if (o1<o2) {
				return -1;
			}else {
				return 0;
			}
		}
	});
	static PriorityBlockingQueue<Double> buy = new PriorityBlockingQueue<Double>(10000, new Comparator<Double>() {
		@Override
		public int compare(Double o1, Double o2) {
			if(o1>o2){
				return -1;
			}else if (o1<o2) {
				return 1;
			}else {
				return 0;
			}
		}
	});
	public static void name() {
		
	}
	public static void main(String[] args) {
	
	}
}
