package com.djh.test.jfreechart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class RandomNumber {

	static int N = 4000;
	static int piN = (int) (Math.PI*1000);
	public static Map<Integer, Integer> getMap() {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for(Integer i=-N;i<=N;){
			map.put(i, 0);
			i = i+1;
		}
		return map;
	}
	public static Map<Integer, Integer> getGaussian() {
		Map<Integer, Integer> map = getMap();
		Random random = new Random();
		for(int i=0;i<1000000;i++){
			double r = random.nextGaussian();
			//BigDecimal deSource = new BigDecimal(r*1000);
			int r1 = getScale(r*1000);//deSource.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
			//System.out.println();
			if(map.get(r1)==null && r1<-N){
				map.put(-N, map.get(-N)+1);
			}else if (map.get(r1)==null && r1>N) {
				map.put(N, map.get(N)+1);
			}else {
				map.put(r1, map.get(r1)+1);
			}
		}
		return map;
	}
	
	public static Map<Integer, Double> getSin() {
		double p = Math.PI;
		Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for(double i=-p;i<=p;){
			double value = Math.sin(i)*1000;
			map.put((int)(i*1000), value);
			i = i+0.001;
		}
		return map;
	}
	public static Map<Integer, Double> getCos() {
		double p = Math.PI;
		Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for(double i=-p;i<=p;){
			double value = Math.cos(i)*1000;
			map.put((int)(i*1000), value);
			i = i+0.001;
		}
		return map;
	}
	public static Map<Integer, Double> getTan() {
		final int limit =100000;
		double p = Math.PI;
		Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for(double i=-p;i<=p;){
			double value = Math.tan(i)*1000;
			if(Math.tan(i)*1000>limit){
				value = limit;
			}else if (Math.tan(i)*1000<-limit) {
				value = -limit;
			}
			
			map.put((int)(i*1000), value);
			i = i+0.001;
		}
		return map;
	}
	public static int getScale(double a) {
		if(a>0){
			return (int)(a+0.5);
		}else if(a<0){
			return (int)(a-0.5);
		}else{
			return (int) a;
		}
	}
	public static double getScale(double a,int scale) {
		double k = Math.pow(10, scale);
		if(a>0){
			return (int)(a*k+0.5)/k;
		}else if(a<0){
			return (int)(a*k-0.5)/k;
		}else{
			return  a;
		}
	}
	public static void main(String[] args) {
		
//		Map<Integer, Integer> map = getGaussian();
//		for(Integer v:map.keySet()){
//			System.out.println(v+"\t"+map.get(v));
//		}
		//System.out.println(getScale(-1.51));
		//System.out.println(getScale(Math.sin(Math.PI/6),3));
		System.out.println(getScale(Math.sin(Math.PI*90/180),4));
	}

}
