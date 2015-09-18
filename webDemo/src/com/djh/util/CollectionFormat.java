package com.djh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 集合（Map,List,Set）的格式化输出
 * 弱化版的json格式
 * @author djh
 */
public class CollectionFormat {

	@SuppressWarnings("unchecked")
	public static String toString(Object object) {
		if(object instanceof Map){
			return mapFormat((Map<String, Object>)object);
		}else if (object instanceof List) {
			return listFormat((List<Object>)object);
		}else if (object instanceof Set) {
			return listFormat((List<Object>)object);
		}else if (object instanceof String) {
			return escape(object.toString());
		}else if (object instanceof Double) {
			return object.toString();
		}else if (object instanceof Integer) {
			return object.toString();
		}else if (object instanceof Character) {
			return escape(object.toString());
		}else if (object instanceof Boolean) {
			return object.toString();
		}else {
			System.out.println("不支持该类型！");
			logger.info("===========错误类型==========="+object.getClass().getName());
		}
		return null;
	}
	//map格式化
	private static String mapFormat(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer("{");
		for(String key:map.keySet()){
			sb.append(escape(key)).append(":");
			//递归调用
			String value = toString(map.get(key));
			sb.append(value).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString();
	}
	//list，set格式化
	private static String listFormat(List<Object> list) {
		StringBuffer sb = new StringBuffer("[");
		for(Object item:list){
			//递归调用
			String value = toString(item);
			sb.append(value).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	/**
	 * 转义'{'   '}'   ','    ':'  '['  ']'特殊字符  
	 * 		转义后	\{ 	\}	\,	\: \[  \[
	 * '\n'  '\t'  '\'  '\r'
	 * 		转义后	\n 	\t	\\	\r
	 * @param args
	 */
	public static String escape(String string) {
		String newString = string.replaceAll("\\\\", "\\\\\\\\")
			.replaceAll("\\{", "\\\\{")
			.replaceAll("\\}", "\\\\}")
			.replaceAll("\\[", "\\\\[")
			.replaceAll("\\]", "\\\\]")
			.replaceAll(",", "\\\\,")
			.replaceAll(":", "\\\\:")
			.replaceAll("\n", "\\\\n")
			.replaceAll("\t", "\\\\t")
			.replaceAll("\r", "\\\\r");
		return newString;
	}
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		list.add(true);
		list.add("item1\\\\");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("hh", "fdfi");
		map.put("map", map1);
		map.put("ab,}c", 3);
		map.put("d", 1.4);
		
		map.put("2", '5');
		map.put("li[st", list);
		map.put("43", 43);
		
		System.out.println(toString(map));
	}
	static Logger logger = LoggerFactory.getLogger(CollectionFormat.class);
}
