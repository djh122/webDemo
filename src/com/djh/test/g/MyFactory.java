package com.djh.test.g;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyFactory {

	private static List<Class<?>> list = new ArrayList<Class<?>>();
	
	public static void register(Class<?> class1) {
		list.add(class1);
	}
	public static List<Class<?>> getList() {
		return list;
	}
	/**
	 * 执行注册在工厂里包含@RMap(name)的函数
	 * 根据注解自动匹配注入属性
	 * @param name
	 * @param params
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object exe(String name,Map<String, String> params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<MethodBean> list1 = new ArrayList<MyFactory.MethodBean>();
		for(Class<?> class1:list){
			Method[] methods = class1.getDeclaredMethods();
			for(Method method:methods){
				RMap m = method.getAnnotation(RMap.class);
				if(m != null && m.value().equals(name)) {
					Object obj = class1.newInstance();
					Class<?>[] mParams = method.getParameterTypes();
					Annotation[][] annos = method.getParameterAnnotations();
					Object[] args = new Object[mParams.length];
					for(int i=0;i<mParams.length;i++){
						PM pm = null;
						Annotation[]  ans = annos[i];
						for(int k=0;k<ans.length;k++){
							if(ans[k] instanceof PM){
								pm = (PM) ans[k];
								break;
							}
						}
						if(pm != null){
							if(mParams[i].equals(String.class)){
								args[i] = params.get(pm.value());//只支持基础类型；
							}else if (mParams[i].equals(Double.class)) {
								args[i] = Double.parseDouble(params.get(pm.value()));
							}else if (mParams[i].equals(Integer.class)) {
								args[i] = Integer.parseInt(params.get(pm.value()));
							}
						}else {
							Object bean = mParams[i].newInstance();
							Field[] fields = mParams[i].getDeclaredFields();
							for(int j=0;j<fields.length;j++){
								String fieldName = fields[j].getName();
								if(params.get(fieldName) != null){
									fields[j].setAccessible(true);
									fields[j].set(bean, params.get(fields[j].getName()));; 
								}
							}
							args[i] = bean;
						}
					}
					Object rs = method.invoke(obj, args);
					return String.valueOf(rs);
				}
			}
		}
		for(MethodBean mb:list1){
			Object obj = mb.class1.newInstance();
			mb.methodName.invoke(obj);
		}
		return null;
	}
	static class MethodBean{
		Class<?> class1;
		Method methodName;
		Class<?>[] params;
		String annoName;
	}
}
