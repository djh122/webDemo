package com.djh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import net.sf.json.JSONObject;

public class U {

	/**
	 * json格式化
	 * @param object
	 * @return
	 */
	public static String jsonFormat(Object object) {
		return JSONObject.fromObject(object).toString();
	}
	/**
	 * 日期格式化
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateFormat(Date date,String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * 将字符串 经md5加密转换 为16进制字小写符串
	 * @param data 原字符串
	 * @return
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}
	
}
