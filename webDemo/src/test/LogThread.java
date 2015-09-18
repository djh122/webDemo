package test;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogThread extends Thread{

	//先进先出队列
	public static List<Map<String, Object>> logs = new Vector<Map<String,Object>>();
	public static int logCount = 0;
	
	@Override
	public void run() {
		while (true) {
			if(logs.size()>0){
				//System.out.println("三秒内请求数："+logs.size());
				Map<String, Object> log = logs.remove(0);
				logCount++;
				if(logCount%500==0){
					System.out.println("======处理==========="+logCount);
				}
			}else {
				try {
					Thread.sleep(3000);//无请求睡眠三秒后执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static Logger logger = LoggerFactory.getLogger(LogThread.class);
}
