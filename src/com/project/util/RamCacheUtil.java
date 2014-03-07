/**
 * 
 */
package com.project.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dingjs
 */
public class RamCacheUtil {
	
	private static Map<String,Object> cacheMap = new HashMap<String,Object>();
	
	public static Object getCache(String cacheKey) {
		if(cacheMap.containsKey(cacheKey)) {
			return cacheMap.get(cacheKey);
		}
		return null;
	}
	
	public static void put(String key,Object object) {
		cacheMap.put(key, object);
	}
	
	public static void remove(String key) {
		cacheMap.remove(key);
	}
}
