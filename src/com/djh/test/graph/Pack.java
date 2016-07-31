package com.djh.test.graph;

public class Pack {
	public static void foo() {
		//2,2,6,5,4，它们的价值分别是6,3,5,4,6
		int[] weight = {2,2,6,5,4,1,3};
		int[] value = {6,3,5,4,6,7,4};
		int tw = 10;
		int[][] m = new int[tw+1][weight.length];
		for(int i=1;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				if(i-weight[j]>=0){//当该物品能放入是，比较与之前该重量下最大价值，如果更大则更新
					if(j==0){//当只有一件物品，则放入
						m[i][j] = value[j];
					}else {
						//为了放入新物品，可能需要将原来的拿出来，腾出特定空间后持有最大值+该物品的价值>不腾空间，则更新
						int v_newjoin = m[i-weight[j]][j-1]/*这句很重要*/+value[j];
						if(v_newjoin>m[i][j-1]){
							m[i][j] = v_newjoin;//置换新物品
						}else {
							m[i][j] = m[i][j-1];//维持原背包不变
						}
					}
				}else {
					if(j==0){//当只有一件物品且不能放入，背包值为0
						m[i][j] = 0;
					}else {
						m[i][j] = m[i][j-1];//不能放入物品，背包为原值
					}
				}
			}
		}
		//打印输出
		for(int i=1;i<m.length;i++){
			System.out.print(i+"\t|");
			for(int j=0;j<m[0].length;j++){
				System.out.print(m[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println();
		for(int i=0;i<weight.length;i++){
			System.out.print(weight[i]+"\t");
		}
		System.out.println();
		for(int i=0;i<weight.length;i++){
			System.out.print(value[i]+"\t");
		}
		System.out.println();
		//还原出背包中持有物品
		int maxcol = m[0].length-1;
		int[] choice = new int[weight.length];
		int maxrow = m.length-1;
		int max = m[maxrow][maxcol];
		x:for(;;){
			for(int j=0;j<=maxcol;j++){
				if(m[maxrow][j]==max){
					choice[j] = 1;
					max = max - value[j];
					if(max==0){
						break x;
					}
					maxrow = maxrow - weight[j];
				}
			}
		}
		for(int i=0;i<weight.length;i++){
			System.out.print(choice[i]+"\t");
		}
	}
	
	public static void main(String[] args) {
		foo();
	}
}
