package com.djh.test.graph;

import java.util.Arrays;

public class Dp {

	public static void dynamic() {
		int[][] data = {{18,38,24},{14,35,22},{10,31,21}};
		final int N = 9;
		int t = 0;
		int leave = 9;
		int[] rs = new int[3];
		for(int i=0;i<3;i++){
			t = t+data[i][1];
			leave = leave-(i+2);
			int min = 0;
			for(int k=0;k<2;k++){
				leave = leave-(k+2);
				int val = data[k][1]+data[leave-2][2];
				if(leave>2){
					if(rs[1]==0){
						min = val;
						rs[1]=k+2;
						rs[2]=leave-2-k;
					}else if(val<min){
						rs[1]=k+2;
						rs[2]=leave-2-k;
					}
				}
			}
			if(rs[0]==0){
				t += min;
				rs[0] = i+2;
			}else {
				//
			}
		}
	}
	
	public static void kk() {
		int[][] data = {{18,38,24},{14,35,22},{10,31,21}};
		final int N = 9;
		int t = 0;
		for(int j=0;j<3;j++){
			t += data[0][j];
		}
		int start = 0;
		int end = 3;
		int[] rs = new int[3];
//		for(int i=0;i<3;i++){
//			rs[i] = 2;
//		}
		
		while(start != end){
			int max = 0;
			int index = 0;
			for(int i=0;i<3;i++){
				if((rs[i]+1)<3){
					if(max==0){
						max = data[rs[i]][i];
						index = i;
					}else if(max<data[rs[i]][i]){
						max = data[rs[i]][i];
						index = i;
					}
				}
			}
			rs[index] = rs[index]+1;
			start++;
		}
		System.out.println(t);
		System.out.println(Arrays.toString(rs));
	}
	public static void main(String[] args) {
		kk();
	}

}
