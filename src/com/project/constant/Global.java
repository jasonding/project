/**
 * 
 */
package com.project.constant;


/**
 * 全局常量
 * @author dingjs
 */
public class Global {
	/**
	 * 常量 字符串空 
	 */
	public static final String STRING_EMPTY = "";
	
	/**
	 * 常量 字符串0 
	 */
	public static final String STRING_ZERO = "0";
	
	/**
	 * 常量 null 
	 */
	public static final String STRING_NULL = null;

	/**
	 * 默认20 为每一页显示记录数
	 */
	public static final Integer INTEGER_DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 默认 1 为当前页码
	 */
	public static final Integer INTEGER_DEFAULT_CURRENT_PAGE = 1;
	
	/**
	 * 常量 整形0 
	 */
	public static final Integer INTEGER_ZERO = 0;
	
	private static Global instance = null;
	
	public static Global getInstace() {
		if(instance == null) {
			synchronized (Global.class) {
				if(instance == null) 
					instance = new Global();	
			}
		}
		return instance;
	}
	
}
