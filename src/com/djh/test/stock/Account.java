package com.djh.test.stock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Account {

	private static DecimalFormat df = new DecimalFormat("0.0");
	private BigDecimal wait;
	private BigDecimal capital;
	private BigDecimal in;
	private BigDecimal gz;
	private List<Integer> record = new LinkedList<Integer>();
	public int b_c = 0;
	public int c_c = 0;
	public Account(BigDecimal wait,BigDecimal in,BigDecimal gz){
		this.wait = wait;
		this.in = in;
		this.gz = gz;
		this.capital = wait;
	}
	public void increase() {
		capital = capital.add(gz);
		wait = wait.add(gz);
	}
	public void buy(BigDecimal b) {
		if(wait.compareTo(b)!=-1){
			b_c++;
			wait = wait.subtract(b);
			in = in.add(b);
		}
	}
	public void clear(BigDecimal c) {
		c_c++;
		in = in.subtract(c);
		wait = wait.add(c);
	}
	public BigDecimal getTotal() {
		return in.add(wait);
	}
	
	public BigDecimal getWait() {
		return wait;
	}
	public BigDecimal getIn() {
		return in;
	}
	public BigDecimal getCapital() {
		return capital;
	}
	public void updateIn(BigDecimal swing) {
		in = swing.multiply(in);
	}
	
	public int addRecord(BigDecimal b) {
		int flag = 0;
		if(b.compareTo(BigDecimal.ZERO)==1){
			flag = 1;
		}else if (b.compareTo(BigDecimal.ZERO)==-1) {
			flag = -1;
		}
		if(record.size()<10){
			record.add(flag);
		}else {
			int t = 0;
			for(int i=0;i<10;i++){
				t += record.get(i);
			}
			if(t>3 && flag==-1){
				return -1;
			}else if(t>3 && flag==1){
				return 1;
			}else if (t<-3 && flag==1) {
				return 1;
			}else if (t<-3 && flag==-1){
				return -1;
			}
			record.remove(0);
			record.add(flag);
		}
		return 0;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("总金额=").append(df.format(getTotal()))
			.append("||||无投资=").append(df.format(getCapital()))
			.append("||||在仓中=").append(df.format(getIn()))
			.append("||||空仓中=").append(df.format(getWait()));
		System.out.println(sb.toString());
		return sb.toString();
	}
}
