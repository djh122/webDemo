package com.djh.test.stock.stat;

public class MyMathUtils {

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
	public static double[] getScala(double[] a,int scala) {
		for(int i=0;i<a.length;i++){
			a[i] = getScala(a[i], scala);
		}
		return a;
	}
	
}
