package com.djh.test.matrix;

public class Mul {

	public static void show(double[][] a) {
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	//矩阵相乘
	public static double[][] mul(double[][] a,double[][] b) {
		if(a[0].length!=b.length){
			throw new RuntimeException("两矩阵不可乘");
		}
		//取a的行数，b的列数组成结果矩阵
		int row = a.length;
		int col = b[0].length;
		double[][] t = new double[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				double temp = 0;
				//可乘的矩阵必需a的列数=b的行数，对a的行数、b的列数无限制
				for(int n=0;n<a[0].length;n++){
					temp += a[i][n] * b[n][j];
				}
				t[i][j] = temp;
			}
		}
		show(t);
		return t;
	}
	/**
	 * 矩阵转置
	 * @param a
	 * @return
	 */
	public static double[][] transform(double[][] a) {
		int row = a.length;
		int col = a[0].length;
		double[][] t = new double[col][row];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				t[j][i]=a[i][j];
			}
		}
		show(t);
		return t;
	}
	/**
	 * 线性代数求解
	 * @param a
	 * @return
	 */
	public static double[] cellMatrix(double[][] a) {
		int row = a.length;
		int col = a[0].length;
		if((row+1)!=col){
			throw new RuntimeException("无解或无唯一解");
		}
		double[] r = new double[row];
		//对角线为1，左下角归零
		for(int i=0;i<row;i++){
			for(int j=0;j<=i;j++){
				if(i==j && a[i][j]!=1){
					double base = a[i][j];
					//对角线置于0
					for(int n=0;n<col;n++){
						a[i][n]=a[i][n]/base;
					}
				}else {
					//
					double base= a[i][j];
					for(int n=j;n<col;n++){
						a[i][n] = a[i][n] - a[j][n]*base;
					}
				}
			}
		}
		//右上角归零
		for(int i=0;i<row-1;i++){
			for(int j=i+1;j<row;j++){
				double k= a[i][j];
				for(int n=j;n<col;n++){
					if(a[i][n]==0) continue;
					a[i][n] = a[i][n] - a[j][n]*k;
				}
			}
		}
		show(a);
		for(int i=0;i<row;i++){
			r[i]=a[i][row];
		}
		//show(r);
		return r;
		
	}
	//交换行
	public static double[][] exchangeRow(double[][] a,int r1,int r2) {
		int col = a[0].length;
		//矩阵右边 加单元矩阵
		for(int j=0;j<col;j++){
			double temp = a[r2][j];
			a[r2][j] = a[r1][j];
			a[r1][j] = temp;
		}
		return a;
	}
	//交换列
	public static double[][] exchangeCol(double[][] a,int c1,int c2) {
		int row = a.length;
		//矩阵右边 加单元矩阵
		for(int i=0;i<row;i++){
			double temp = a[c2][i];
			a[c2][i] = a[c1][i];
			a[c1][i] = temp;
		}
		return a;
	}
	
	public static double det2(double a11,double a12,double a21,double a22) {
		return 1/(a11*a12-a12*a21);
	}
//	public static double cramar(,double a22) {
//		return 1/(a11*a12-a12*a21);
//	}
	//矩阵求逆
	public static double[][] inv(double[][] a) {
		int row = a.length;
		int col = a[0].length;
		double[][] t = new double[row][col];
		double[][] c = new double[row][col*2];
		//矩阵右边 加单元矩阵
		for(int i=0;i<row;i++){
			for(int j=0;j<col*2;j++){
				if(j<row){
					c[i][j]=a[i][j];
				}else {
					if((j-col)==i){
						c[i][j]=1;
					}else {
						c[i][j]=0;
					}
				}
			}	
		}
		//对角线为1，左下角归零
		for(int i=0;i<row;i++){
			for(int j=0;j<=i;j++){
				if(i==j){
					double base = c[i][j];
					for(int n=0;n<col*2;n++){
						c[i][n]=c[i][n]/base;
					}
				}else {
					double base= c[i][j];
					for(int n=0;n<col*2;n++){
						c[i][n] = c[i][n] - c[j][n]*base;
					}
				}
			}
		}
		//右上角归零
		for(int i=0;i<row-1;i++){
			for(int j=i+1;j<col;j++){
				double k= c[i][j];
				for(int n=0;n<col*2;n++){
					c[i][n] = c[i][n] - c[j][n]*k;
				}
			}
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				t[i][j]=c[i][j+col];
			}	
		}
		show(t);
		return t;
	}
	public static void main(String[] args) {
//		double[][] a = {{1,2,3}};//{{1,3},{2,7}};
//		double[][] b = {{1,1},{2,1},{3,1}};
//		mul(a,b);
//		double[][] a = {{2,3,5,10},{3,4,7,14},{1,1,1,4}};
//		cellMatrix(a);
		double[][] mtx_a = {{1,2,4},{1,4,8},{1,1,1}};
//		double[][] mtx_ai = inv(mtx_a);
//		mul(mtx_a, mtx_ai);
//		mul(mtx_ai, mtx_a);
		transform(mtx_a);
	}

}
