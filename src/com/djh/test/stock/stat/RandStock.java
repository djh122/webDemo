package com.djh.test.stock.stat;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandStock implements Runnable{

	public static double increase_v = 0.00028;//每个交易日平均涨幅
	public static double increase = 0.00036;//每个交易日平均涨幅
	public static double max = 0.1;
	public static double min = -0.1;
	public static double start = 1000;
	public static double startf = 1000;
	public static int tran_day =4000;
	
	static Random random = new Random();
	
	public static void generate1() {
		for(int i=1;i<=tran_day;i++){
			double s = getS();
			if(start>startf*2){
				s = -Math.abs(s);
				System.out.println(s+"---------------");
			}else if(start<startf/2){
				s = Math.abs(s);
				System.out.println(s+"+++++++++++++++");
			}
			start = start*(1+s);
			start = getScala(start, 1);
			
			startf = startf*(1+increase_v);
			startf = getScala(startf, 1);
			//if(i%250==0)
			System.out.println(start+"交易日"+i+"==="+startf+"ssss"+getScala(s, 4));
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
	}
	
	public static void generate() {
		Map<Double, Integer> map = new TreeMap<Double, Integer>();
		for(int i=0;i<10000;i++){
			double n = random.nextGaussian();
			n = n+increase;
			if(map.get(n)==null){
				map.put(n, 1);
			}else {
				map.put(n, map.get(n)+1);
			}
		}
		System.out.println(map.toString());
	}
	public static double getS() {
		double n = random.nextGaussian();
		n = n/40;
		n = n+increase;
		if(n>0.1){
			n=0.1;
		}else if (n<-0.1) {
			n=-0.1;
		}
		return getScala(n, 5);
	}
	public static double getScala(double a,int scala) {
		double bs = Math.pow(10, scala);
		double b = a*bs;
		int i=0;
		if(b>0){
			i = (int)(b+0.5);
		}else if (b<0) {
			i = (int)(b-0.5);
		}else {
			i=0;
		}
		return i/bs;
	}
	public static void main(String[] args) {
		new Thread(new RandStock()).start();
	}

	@Override
	public void run() {
		generate1();
		
	}
}
