package com.djh.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 用户日志统一用【info】级别，记录在统一文件（devlog）文件，方便查找
 * @author djh
 *
 */
public class LogUtil {

	private static Map<Class<?>, Logger> logMap = new HashMap<Class<?>, Logger>();
	
	
	public static <T> Logger getLogger(Class<T> class1) {
		if(class1==null){
			return Logger.getRootLogger();
		}
		Logger logger = logMap.get(class1);
		if(logMap.get(class1) == null){
			logger = Logger.getLogger(class1);
			logMap.put(class1, logger);
		}
		return logger;
	}
	public static <T> Logger info(String msg,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("记录日志："+msg);
		return logger;
	}
	public static <T> Logger info(String msg,Throwable throwable,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("记录日志："+msg,throwable);
		return logger;
	}
	public static <T> Logger error(String msg,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("错误日志："+msg);
		return logger;
	}
	public static <T> Logger error(String msg,Throwable throwable,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("错误日志："+msg,throwable);
		return logger;
	}
	
	public static <T> Logger warn(String msg,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("警告日志："+msg);
		return logger;
	}
	public static <T> Logger warn(String msg,Throwable throwable,Class<T> class1) {
		Logger logger = getLogger(class1);
		logger.info("警告日志："+msg,throwable);
		return logger;
	}
//
//	public static <T> Logger info(String msg) {
//		Logger logger = getLogger(null);
//		logger.info("错误日志："+msg);
//		return logger;
//	}
//	public static <T> Logger info(String msg,Throwable throwable) {
//		return info(msg,throwable, null);
//	}
//	public static <T> Logger error(String msg) {
//		Logger logger = getLogger(null);
//		logger.info("错误日志："+msg);
//		return logger;
//	}
//	public static <T> Logger error(String msg,Throwable throwable) {
//		return error(msg,throwable, null);
//	}
//	
//	public static <T> Logger warn(String msg) {
//		Logger logger = getLogger(null);
//		logger.info("警告日志："+msg);
//		return logger;
//	}
//	public static <T> Logger warn(String msg,Throwable throwable) {
//		Logger logger = getLogger(null);
//		logger.info("警告日志："+msg,throwable);
//		return logger;
//	}
	public static void main(String[] args) {
		//String path = System.getProperty("user.dir")+"/config/";
		//PropertyConfigurator.configure(path+"log4j.xml");
		//LogUtil.info("hh", LogUtil.class);
		//LogUtil.error("hhh", LogUtil.class);
		Logger.getRootLogger().info("aaa");
	}
}
