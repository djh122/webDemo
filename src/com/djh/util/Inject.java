package com.djh.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.djh.exception.ParamException;


public class Inject{

	private static String date_format = "yyyy-MM-dd";
	

	/**
	 * 把request参数转为bean
	 * @param request
	 * @param cla
	 * @return
	 */
	public static <T> T inject(HttpServletRequest request,Class<T> cla) {
		Map<String, Object> params = param2Map(request);
		
		T obj = null;
		try {
			obj = cla.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
			new ParamException("初始化bean异常");
		} 
		Field[] fields = cla.getDeclaredFields();
		Method[] methods = cla.getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>();
		for(Method method:methods){
			methodMap.put(method.getName(), method);
		}
		
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();
			System.out.println(fieldType);
			String fieldNameUpper = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			String methodName = "set"+fieldNameUpper;
			Method method = methodMap.get(methodName);
			if( method != null && params.get(fieldName)!=null){
				try {
					method.invoke(obj, transType(params.get("fieldName").toString(), fieldType));
				} catch (Exception e) {
					e.printStackTrace();
					throw new ParamException("参数注入异常");
				}
			}
		}
		return obj;
	}
	/*把request参数转化为Map*/
	@SuppressWarnings("unchecked")
	public static Map<String, Object> param2Map(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Enumeration<String> names = request.getParameterNames();
		for (Enumeration<String> e = names; e.hasMoreElements();) {
			String thisName = e.nextElement();
			String thisValue = request.getParameter(thisName);
			param.put(thisName, thisValue);
		}
		
		return param;
	}
	public static <T> T map2bean(Map<String, String> params,Class<T> cla) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
		
		T obj= cla.newInstance();
		Field[] fields = cla.getDeclaredFields();
		Method[] methods = cla.getMethods();
		
		Map<String, Method> methodMap = new HashMap<String, Method>();
		for(Method method:methods){
			System.out.println(method.getName());
			methodMap.put(method.getName(), method);
		}
		
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();
			System.out.println(fieldType);
			String fieldNameUpper = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			String methodName = "set"+fieldNameUpper;
			Method method = methodMap.get(methodName);
			if( method != null && params.get(fieldName)!=null){
				method.invoke(obj, transType(params.get(fieldName), fieldType));
			}
		}
		return obj;
	}
	
	private static Object transType( String param,Class<?> cls) throws ParseException {
		Object result = null;
		if(cls.getName().equals(String.class.getName())){
			result = param;
		}else if (cls.getName().equals(int.class.getName())) {
			result = Integer.valueOf(param);
		}else if (cls.getName().equals(Integer.class.getName())) {
			result = Integer.valueOf(param);
		}else if (cls.getName().equals(BigDecimal.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(long.class.getName())) {
			result = Long.valueOf(param);
		}else if (cls.getName().equals(Long.class.getName())) {
			result = Long.valueOf(param);
		}else if (cls.getName().equals(double.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(Double.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(float.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(Float.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(Character.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(char.class.getName())) {
			result = new BigDecimal(param);
		}else if (cls.getName().equals(java.util.Date.class.getName())) {
			result = new SimpleDateFormat(date_format).parseObject(param);
		}else if (cls.getName().equals(java.sql.Date.class.getName())) {
			result = new SimpleDateFormat(date_format).parseObject(param);
		}else{
			throw new RuntimeException("request参数类型转换不支持："+cls.getName()+",如有需要请手动添加该类型");
		}
		return result;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
		
	}
}
