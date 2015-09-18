package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestThread implements Runnable {
	public static  int count = 0;
	List<Map<String, Object>> list = LogThread.logs;
	@Override
	public void run(){
		while (true) {
			//synchronized (list) {
				list.add(new HashMap<String, Object>());
			//}
			count++;
			if(count%500==0){
				System.out.println("进入的请求数"+count);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			new Thread(new RequestThread()).start();
		}
		new LogThread().start();
	}
}
