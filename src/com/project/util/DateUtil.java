/**
 * 
 */
package com.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dingjs
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String DATE_PATTERN = "yyyy-MM-dd";
	
	/**
	 * 根据指定的格式,格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date,String pattern) {
		if(date == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String formatDate = dateFormat.format(date);
		return formatDate;
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date) {
		if(date == null) {
			return null;
		}
		return format(date,DATE_TIME_PATTERN);
	}
	
	public static Date pahseDate(String dateStr,String pattern) {
		if(dateStr == null || "".equals(dateStr.trim())) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error("解析时间出错[dateStr={}]",dateStr,e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		String str = "210111-12";
		pahseDate(str,DATE_TIME_PATTERN);
	}
}
