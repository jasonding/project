/**
 * 
 */
package com.project.util;

import java.util.Collection;
import java.util.Map;


/**
 * @author dingjs
 */
public class NullUtil {
	/**
	 * 判断一个对象是否空
	 * @param t
	 * @return
	 */
	public static <T> boolean isNull(T t) {
		if(t == null) 
			return true;
		else 
			return false;
	}
	
	/**
	 * 判断一个字符串是否是null或者是空串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || "".equals(str.trim())) 
			return true;
		else 
			return false;
	}

	/**
	 * 判断一个集合是否是null或者是空集合
	 * @param str
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		if(collection == null || collection.isEmpty()) 
			return true;
		else 
			return false;
	}
	
	/**
	 * 判断Map是否是null或者是空集合
	 * @param str
	 * @return
	 */
	public static <K,V> boolean isNullOrEmpty(Map<K,V> map) {
		if(map == null || map.isEmpty()) 
			return true;
		else 
			return false;
	}
}
