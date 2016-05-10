package com.djh.common.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	//private Map<String, Object> entry = new HashMap<String, Object>();
	private String key;
	private Object value;
	private long expire = 0;
	/**
	 * 添加缓存
	 * @param key
	 * @param value
	 * @param expire 过期时间（秒）
	 */
	public Cache(String key,Object value,long expire){
		expire = expire*1000;
		this.key = key;
		this.value = value;
		this.expire = System.currentTimeMillis()+expire;
	}
//	public Cache(Object value,long expire){
//		expire = expire*1000;
//		this.value = value;
//		this.expire = System.currentTimeMillis()+expire;
//	}
	public Object getValue() {
		return value;
	}
	public boolean isExpire() {
		if(System.currentTimeMillis()>expire){
			CacheManager.removeCache(key);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object obj) {  
		if(obj instanceof Cache ){
			if(((Cache) obj).key.equals(this.key)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	@Override
    public int hashCode() {  
        int h = 0;  
        char val[] = key.toCharArray();  
        int len = val.length;  
        for (int i = 0; i < len; i++) {  
            h = 31*h + val[i];  
        }  
        return h; 
	}
	public static void main(String[] args) {
		System.out.println(new Cache("djh", "aaa",0).equals(new Cache("djh", "bbb", 0)));;
		System.out.println(new Cache("djh", "aaa",0).hashCode());
		System.out.println(new Cache("djh", "bbb", 0).hashCode());
		;
	}
	public String getKey() {
		return key;
	}
}
