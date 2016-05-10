package com.djh.common.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

	private static Map<String, Cache> entry = new HashMap<String, Cache>();
	
	public static void addCache(Cache cache) {
		entry.put(cache.getKey(), cache);
	}
	
	public static Cache getCache(String key) {
		return entry.get(key);
	}
	public static void removeCache(String key) {
		entry.remove(key);
	}
	public static boolean isHit(String key) {
		Cache cache = CacheManager.getCache(key);
		if(cache!=null && !cache.isExpire()){
			return true;
		}else {
			return false;
		}
	}
	public static boolean isHit(Cache cache) {
		if(cache!=null && !cache.isExpire()){
			return true;
		}else {
			return false;
		}
	}
}
