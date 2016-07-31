package com.djh.test.s;

public class Bean{
	int x;
	int y;
	int val;
	public Bean(int x,int y,int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
	@Override
	public String toString() {
		return String.valueOf(val);
	}
}