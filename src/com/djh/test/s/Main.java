package com.djh.test.s;


public class Main {

	private Bean[][] country = new Bean[50][50];
	public void init() {
		for(int i=0;i<50;i++){
			for(int j=0;j<50;j++){
				country[i][j]=new Bean(i,j,0);
			}
		}
	}
	public void update() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		thread.start();
	}
	public void change() {
		Bean start1 = country[12][37];
		start1.val = 7;
		
		Bean start2 = country[37][12];
		start2.val = 7;
		int x1 = 12;
		int y1 = 37;
		int x2 = 37;
		int y2 = 12;
		for(int i=1;i<=6;i++){
			
		}
	}
	private void k(Bean[][] country,int lt_x,int lt_y,int len,int val){
		for(int i=0;i<len;i++){
			country[lt_x][lt_y+i].val = val;
		}
		for(int i=0;i<len;i++){
			country[lt_x+len][lt_y+i].val = val;
		}
		for(int i=1;i<len-1;i++){
			country[lt_x+i][lt_y].val = val;
		}
		for(int i=1;i<len-1;i++){
			country[lt_x+i][lt_y+len].val = val;
		}
	}
	public static void main(String[] args) {
		
	}
	
	
	
	
	
	
}

