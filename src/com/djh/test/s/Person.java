package com.djh.test.s;

public class Person {
	int x;
	int y;
	int start_val;
	int see_dist;
	int move_max;
	int now_val;
	int cosume_val;
	public Person(int x,int y,int start_val,int see_dist,int cosume_val) {
		this.x = x;
		this.y = y;
		this.start_val = start_val;
		this.see_dist = see_dist;
		this.cosume_val = cosume_val;
	}
	public int cosume() {
		now_val = now_val - cosume_val;
		return now_val;
	}
	public int get(int val) {
		now_val += val;
		return now_val;
	}
}
