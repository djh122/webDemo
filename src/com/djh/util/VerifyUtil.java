package com.djh.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 用途：参数验证
 * 2015-8-17
 * @author daijihe
 */
public class VerifyUtil {

	/**
	 * 验证是否所有参数都不为空，有任意参数为空则返回true；
	 * @param params
	 * @return
	 */
	public static boolean isNull(Object ...params) {
		for(Object param:params){
			if(param==null ||"".equals(param.toString().trim())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证是否所有参数都为空，有任意参数不为空则返回true；
	 * @param params
	 * @return
	 */
	public static boolean isNotNull(Object ...params) {
		for(Object param:params){
			if(param != null && !("".equals(param.toString().trim()))){
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * 验证是否所有参数都不为空，有任意参数为空则返回true；
	 * @param params
	 * @return
	 */
	public static boolean isNull(Map<String, Object> params,String ...names) {
		if(params ==null){
			return true;
		}
		for(String key:names){
			Object value = params.get(key);
			if(value==null ||"".equals(value.toString().trim())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 不都为空，只要有一个参数不为空就返回真；
	 * @param params
	 * @return
	 */
	public static boolean notAllNull(Map<String, Object> params,String ...names) {
		if(params ==null){
			return false;
		}
		for(String key:names){
			Object value = params.get(key);
			if(!(value==null ||"".equals(value.toString().trim()))){
				return true;
			}
		}
		return false;
	}
	/**
	 * 所有参数都为空返回真；
	 * @param params
	 * @return
	 */
	public static boolean isAllNull(Map<String, Object> params,String ...names) {
		if(params ==null){
			return true;
		}
		for(String key:names){
			Object value = params.get(key);
			if(!(value==null ||"".equals(value.toString().trim()))){
				return false;
			}
		}
		return true;
	}
	/**
	 * 验证字符串是否可转数字，不能转返回false
	 * @param param
	 * @return
	 */
	public static boolean isNumber(String ...params) {
		for(String param:params){
			try {
				Double.parseDouble(param);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 验证是否是数字，不是返回true,是数字返回false
	 * @param param
	 * @return
	 */
	public static boolean isNotNumber(String ...params) {
		for(String param:params){
			try {
				Double.parseDouble(param);
			} catch (Exception e) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证是否是数字，不是返回true,是数字返回false
	 * @param param
	 * @return
	 */
	public static boolean isMobile(String phoneNo) {
		if(isNull(phoneNo)){
			return false;
		}
		Matcher matcher = Pattern.compile("^1[3458][0-9]{9}$").matcher(phoneNo.trim());
		return matcher.matches();
	}
	
	/**
	 * 验证是否是手机号，不是返回true,是数字返回false
	 * @param param
	 * @return
	 */
	public static boolean isNotMobile(String phoneNo) {
		if(isNull(phoneNo)){
			return true;
		}
		Matcher matcher = Pattern.compile("^1[3458][0-9]{9}$").matcher(phoneNo.trim());
		return !matcher.matches();
	}
	/**
	 * 输入金额必须大于0
	 * @param amt
	 * @return
	 */
	public static boolean  isAmount(String amt) {
		try {
			BigDecimal amt_int = new BigDecimal(amt);
			if(amt_int.compareTo(BigDecimal.ZERO) == 1){
				return true;
			}
		} catch (Exception e) {
			logger.error("====输入的金额参数是===="+amt);
		}
		return false;
	}
	public static void main(String[] args) {
		//System.out.println(isAmount("0.21e"));
	}
	
	static Logger logger = LoggerFactory.getLogger(VerifyUtil.class);
}
