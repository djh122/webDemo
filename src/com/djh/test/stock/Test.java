package com.djh.test.stock;

public class Test {

	public static void main(String[] args) {
		int c = 0;
		int k = 0;
		for(int i=0;i<10000;i++){
			int a1 = 10;
			int a2 = 10;
			int count = 0;
			while (a1!=0 && a2!=0) {
				if(Math.random()>0.7){
					a1 +=1;
					a2 -=1;
				}else {
					a2 += 1;
					a1 -= 1;
				}
				count++;
				//System.out.println("a1:"+a1+"   a2:"+a2+"   count:"+count);
			}
			if(a1==0){
				k++;
			}
			c += count;
		}
		System.out.println(c/10000);
		System.out.println(k);
		
		
	}
}
