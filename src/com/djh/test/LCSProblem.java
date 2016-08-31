package com.djh.test;

import java.util.Arrays;

public class LCSProblem {  
    public static void main(String[] args) {  
    	lcs();
    }  
    public static void lcs() {
		char[] a = "大家好，最长子串是什么？".toCharArray();
		char[] b = "你好，我不知道什么是最长子串".toCharArray();
		int lineMax = a.length+1;
		int colMax = b.length+1;
		int[][] acc = new int[lineMax][colMax];
		for(int i=1;i<lineMax;i++){
			for(int j=1;j<colMax;j++){
				if(a[i-1]==b[j-1]){
					acc[i][j] =  acc[i-1][j-1]+1;
				}else {
					if(acc[i-1][j]>acc[i][j-1]){
						acc[i][j] =  acc[i-1][j];
					}else {
						acc[i][j] =  acc[i][j-1];
					}
				}
			}
		}
		printArray(acc);
		//回溯轨迹优先向上，当上和左都走不通的时候即可记录当前节点
		int rsLen = acc[lineMax-1][colMax-1];
		char[] rs = new char[rsLen];
		int k = lineMax - 1;
		int c = colMax - 1;
		while (rsLen!=0) {
			if(acc[k][c]==acc[k-1][c]){
				k--;
			}else if (acc[k][c]==acc[k][c-1]) {
				c--;
			}else {
				rs[rsLen-1] = a[k-1];
				rsLen--;
				k--;
				c--;
			}
		}
		
		System.out.println(Arrays.toString(rs));
	}
    public static void printArray(int[][] a) {
		for(int i=0;i<a.length;i++){
			System.out.println(Arrays.toString(a[i]));
		}
	}
}  