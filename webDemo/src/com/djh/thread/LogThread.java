package com.djh.thread;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogThread extends Thread{

	//先进先出队列
	public static List<Map<String, Object>> logs = new Vector<Map<String,Object>>();
	
	@Override
	public void run() {
		while (true) {
			if(logs.size()>0){
				//System.out.println("三秒内请求数："+logs.size());
				Map<String, Object> log = logs.remove(0);
				
				try {	//因为异常会直接kill掉该线程，必须捕获
					//sysLogDao.insertToRunLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("========系统日志插入异常=====");
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
