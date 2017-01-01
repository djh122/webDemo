package com.djh.test.stock;

import java.util.Random;

public class Kelly {
	static Random random = new Random();
	public static double probalitity() {
		Stat stat = new Stat(Stat.ARITHMETIC, 1, -44.5d, 44.5d);
		stat.setLen(5);
		for(int i=0;i<10000;i++){
			stat.add(random.nextGaussian());
		}
		stat.show();
		return 0;
	}

	public static void kelly() {
		Stat stat = new Stat(Stat.ARITHMETIC, 20, 100d, 250d);
		Stat stat1 = new Stat(Stat.ARITHMETIC, 10, -100d, 100d);
		stat1.setLen(7);
		final double win = 0.01;//猜对盈利
		final double lost = 0.01;//猜错输率
		final double inFee = 0.0005;//入仓费率
		final double outFee = 0.0015;//清仓费率
		final int n = 100000;
		int s = 0;//交易次数
		for(int j=0;j<n;j++){
			double base = 100;
			boolean isRemain = false;//是否在仓
			for(int i=0;i<250;i++){
				double p = Math.random();
				double f1 = (win*p-lost*(1-p))/(win*lost);
				stat1.add(f1);
				if(f1>0){
					if(!isRemain){
						base = base*(1-inFee);
						isRemain = true;
						s++;
					}
					
					if(Math.random()<p){
						base = (1+win) * base;
					}else {
						base = base*(1-lost);
					}
				}else {
					if(isRemain){
						base = base*(1-outFee);
						isRemain = false;
					}
				}
			}
			stat.add(base);
		}
		stat.show();
		stat1.show();
		System.out.println(s/100000);
		//8620
	}
	/**
	 * 最多5%
	 * 3%
	 * @param args
	 */
	public static void main(String[] args) {
		//probalitity();
		kelly();
	}

}
