package com.djh.zz_generator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ioc {

	public static Map<String, String> imitateRequest() {
		Map<String, String>req=new HashMap<String, String>();
		req.put("name", "daijihe");
		req.put("password", "000000");
		return req;
	}
	public static void injectPropertyToTest(Test test) {
		System.out.println(test.name);
		System.out.println(test.password);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz1=Class.forName(Test.class.getName());
		Test test=(Test) clazz1.newInstance();
		
		Field[] fields=clazz1.getFields();
		Method[] methods=clazz1.getMethods();
		Map<String, String> req=imitateRequest();
		for(Field field:fields){
			if(req.get(field.getName())!=null){
				for(Method method:methods){
					if(method.getName().equalsIgnoreCase("set"+field.getName())){
						method.getParameterTypes();
						method.getReturnType();
						method.invoke(test, req.get(field.getName()));
					}
				}
			}
		}
		Class<?> clazz=Class.forName(Ioc.class.getName());
		Ioc ioc=(Ioc) clazz.newInstance();
		Method[]methods2=clazz.getMethods();
		
		injectPropertyToTest(test);
		
		while (true) {
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			String method=scanner.next();
			int mark=0;
			for(Method var:methods2){
				if(method.equals(var.getName())){
					var.invoke(ioc, test);
					break;
				}else if (mark==methods2.length-1) {
					System.out.println("方法未定义");
				}
				mark++;
			}
		}
	}
}
