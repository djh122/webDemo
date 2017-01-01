package com.djh.test.stock;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author djh
 *
 */
public class Stat {

	public final static Integer EXPONENT = 1;
	public final static Integer ARITHMETIC = 2;
	private double max;
	private double min;
	private int len = 0;
	
	private Map<Double, Integer> map = new TreeMap<Double,Integer>();
	public Stat(int slope,int inc,Double min,Double max) {
		
		this.max = max;
		this.min = min;
		for(double i=min;;){
			map.put(i, 0);
			double copyI = i;
			if(slope==EXPONENT){
				i = i*inc;
			}else {
				i = i+inc;
			}
			if(i>max){
				this.max = copyI;
				break;
			}
		}
//		if(EXPONENT == slope){
//			map.put(0d, 0);
//			this.min = 0d;
//		}
	}
	public void add(double data) {
		boolean flag = false;
		double last = min;
		Integer lastVal = map.get(min);
		for(Map.Entry<Double, Integer> entry:map.entrySet()){
			if(data<entry.getKey()){
				map.put(last, lastVal+1);
				flag = true;
				break;
			}else {
				last = entry.getKey();
				lastVal = entry.getValue();
			}
		}
		if(!flag){
			map.put(max, map.get(max)+1);
		}
	}
	public void show() {
		StringBuffer s = new StringBuffer("|");
		StringBuffer v = new StringBuffer("|");
		if(len == 0){
			len = String.valueOf(max).length();
			if(max>=1000000.0){
				len = 9;
			}
		}
		int t = 0;
		for(Entry<Double, Integer> entry:map.entrySet()){//"%"+len+"d"
			s.append(String.format("%"+len+"s",entry.getKey().toString())).append("|");
			v.append(String.format("%"+len+"s",entry.getValue().toString())).append("|");
			t += entry.getValue();
		}
		System.out.println("总数为："+t);
		System.out.println(s);
		System.out.println(v);
	}
	
	public void setLen(int len) {
		this.len = len;
	}
	
	public static void main(String[] args) {
		Stat stat = new Stat(Stat.EXPONENT,10,1d,1e10d);
		for(int i=0;i<1000;i++){
			double k = Math.pow(10, Math.random()*9*Math.random());
			System.out.println(k);
			stat.add(k);
		}
		stat.show();
	}
	
	
	
	
}
