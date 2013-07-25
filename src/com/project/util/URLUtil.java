package com.project.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.constant.WebConstant;
import com.project.pool.thread.ThreadPool;
import com.project.task.ITask;
import com.project.task.impl.URLDownLoadTask;

public class URLUtil {
	private static Logger logger = LoggerFactory.getLogger(URLUtil.class);
	
	private static final String STRING_EMPTY = "";
	private static final String STRING_URL_LINK_CHAR = "&";
	private static final int TIME_OUT = 3000;
	private static final int RESPONSE_OK = 200;
	
	public static void main(String[] args) {
		URLDownload("http://data.lehecai.com/img/football/country/9.gif","c:/djd/9.gif",4);
	}

	/**
	 * 多线程下载，断点续传
	 * @param urlStr 下载地址
	 * @param saveFile 保存文件路径名
	 * @param splitCount 采用几条线程进行下载
	 */
	public static void URLDownload(String urlStr,String saveFile,int splitCount) {
		URL url = null;
		HttpURLConnection con = null;
		long length = 0;
		if(splitCount <= 0) splitCount =1; //采用单线程下载
		List<ITask> downloadTasks = new ArrayList<ITask>(splitCount);
		long statPosition ;
		long endPosition ;
		BufferedReader br = null;
		try {
			url = new URL(urlStr);
			con = (HttpURLConnection) url.openConnection();
			if(con.getResponseCode() == 200) {
				length = con.getContentLength();
			}
			logger.info("文件总大小为：{}" , length);
			File backUpFile = new File(saveFile + ".bak");
			if(backUpFile.exists()) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(backUpFile)));
				String readLine = br.readLine();
				String[] strs = readLine.split(WebConstant.WEB_LINK_CHAR);
				for (int i = 0; i < strs.length ; i++) {
					JSONObject jobj = JSONObject.fromObject(strs[i]);
					for (Object key : jobj.keySet()) {
						JSONObject value = jobj.getJSONObject(key.toString());
						statPosition = value.getLong("startPosition");
						endPosition = value.getLong("endPosition");
						URLDownLoadTask downloadTask = new URLDownLoadTask(statPosition,endPosition,urlStr,saveFile);
						downloadTasks.add(downloadTask);
					}
				}
				backUpFile.delete();
			}else {
				for (int i = 0; i < splitCount; i++) {
					statPosition = i * (length / splitCount);
					if(i != (splitCount-1)) endPosition = (i+1)*(length / splitCount);
					else endPosition = length;
					URLDownLoadTask downloadTask = new URLDownLoadTask(statPosition,endPosition,urlStr,saveFile);
					downloadTasks.add(downloadTask);
				}
			}
			ThreadPool.addTasks(downloadTasks);
		} catch (MalformedURLException e) {
			logger.error("组建URL出错[url={}]", urlStr,e);
		}catch (IOException e) {
			logger.error("I/O流出错[{}]", e.getMessage(),e);
		}finally {
			try {
				if(br != null)br.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
			if(con != null) con.disconnect();
		}
	}
	
	/**
	 * 单线程进行下载文件
	 * @param urlStr
	 * @param savePath
	 */
	public static void URLDownloadSingle(String urlStr,String savePath) {
		URLDownload(urlStr, savePath, 0);
	}
	
	/**
	 * GET方式请求网页数据<br>
	 * 无数据返回NULL
	 * @param urlStr
	 * @param params
	 * @param enc
	 */
	public static String URLGet(String urlStr, String enc , Map<String, String> param) {
		return URLGet(urlStr, enc, param, null, null);
	}
	/**
	 * 模拟请求头GET请求
	 * GET方式请求网页数据<br>
	 * 无数据返回NULL
	 * @param urlStr
	 * @param params
	 * @param enc
	 */
	public static String URLGet(String urlStr, String enc, Map<String, String> param, Map<String, String> paramHeader) {
		return URLGet(urlStr, enc, param, paramHeader, null);
	}
	/**
	 * GET方式请求网页数据<br>无数据返回NULL
	 * @param urlStr 请求地址
	 * @param enc 编码,默认utf8
	 * @param param 请求参数
	 * @param paramHeader 请求头参数
	 * @param timeout 连接超时时间,默认3秒
	 * @return
	 */
	public static String URLGet(String urlStr, String enc ,Map<String,String> param,Map<String,String> paramHeader, Integer timeout){
		if(urlStr == null || STRING_EMPTY.equals(urlStr)) return null;
		StringBuilder urlBuilder = new StringBuilder(urlStr); 
		HttpURLConnection httpConnection = null;
		String responseData = null;
		InputStream is = null;
		if(timeout == null || timeout == 0) timeout = TIME_OUT;
		String paramUrl = getURLParam(param, enc);
		param = null;
		if(paramUrl.length() > 0) {
			if(urlBuilder.indexOf("?") == -1) {
				urlBuilder.append("?").append(paramUrl);
			}else urlBuilder.append(paramUrl);
		}
		
		String totalUrl = urlBuilder.toString();
		urlBuilder = null;//释放引用
		try {
			URL url = new URL(totalUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setRequestMethod("GET");
			httpConnection.setUseCaches(false);
			//设置请求头
			for(Map.Entry<String, String> entry : paramHeader.entrySet()) {
				httpConnection.setRequestProperty(entry.getKey(), entry.getValue());
			}
			
			if(httpConnection.getResponseCode() != RESPONSE_OK) {
				//访问出错
			}
			is = httpConnection.getInputStream();
			String contentEncoding = httpConnection.getContentEncoding();
			if(contentEncoding == null || STRING_EMPTY.equals(contentEncoding)){
				contentEncoding = enc;
			}
			//编码要和请求内容的编码对应 
			responseData = getResponsData(is, enc);
			contentEncoding = null;
		}  catch (MalformedURLException e) {
			logger.error("组建URL出错[url={}]", urlStr,e);
		} catch (IOException e) {
			logger.error("I/O流出错[{}]", e.getMessage(),e);
		} finally {
			if(httpConnection != null) httpConnection.disconnect();
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
		}
		return responseData == null ? null: responseData.toString();
	}
	
	/**
	 * 伪造请求头<br>post请求
	 * @param urlStr
	 * @param enc
	 * @param paramHeader
	 * @return
	 */
	public static String URLPost(String urlStr, String enc, Map<String,String> paramHeader) {
		return URLPost(urlStr, enc, null, paramHeader, null);
	}
	
	/**
	 * 伪造请求头<br>post请求
	 * @param urlStr
	 * @param enc
	 * @param params
	 * @param paramHeader
	 * @return
	 */
	public static String URLPost(String urlStr, String enc, Map<String, String> params, Map<String,String> paramHeader) {
		return URLPost(urlStr, enc, params, paramHeader, null);
	}
	
	/**
	 * 普通的post请求
	 * @param urlStr
	 * @param enc
	 * @param params
	 * @param paramHeader
	 * @param timeout
	 * @return
	 */
	public static String URLPost(String urlStr, String enc, Map<String, String> params, Map<String,String> paramHeader, Integer timeout) {
		if(urlStr == null || STRING_EMPTY.equals(urlStr)) return null;
		HttpURLConnection httpConnection = null;
		String responseData = null;
		InputStream is = null;
		OutputStream outputStream = null;
		if(timeout == null || timeout == 0) timeout = TIME_OUT;
		String param = getURLParam(params, enc);
		OutputStreamWriter outputStreamWriter = null;
		try {
			URL url = new URL(urlStr);
			httpConnection = (HttpURLConnection) url.openConnection();
			
			httpConnection.setConnectTimeout(TIME_OUT);
			httpConnection.setRequestMethod("POST");
			httpConnection.setDoOutput(true);
			
			//设置请求头
			for(Map.Entry<String, String> entry : paramHeader.entrySet()) {
				httpConnection.setRequestProperty(entry.getKey(), entry.getValue());
			}
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConnection.setRequestProperty("Content-Length",param.length() + "");
			
			outputStream = httpConnection.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(outputStream, enc);
			outputStreamWriter.write(param);
			outputStreamWriter.flush();
			
			if(httpConnection.getResponseCode() != RESPONSE_OK) {
				//访问出错
			}
			is = httpConnection.getInputStream();
			String contentEncoding = httpConnection.getContentEncoding();
			if(contentEncoding == null || STRING_EMPTY.equals(contentEncoding)){
				contentEncoding = enc;
			}
			 responseData = getResponsData(is, enc);
			contentEncoding = null;
		} catch (MalformedURLException e) {
			logger.error("组建URL出错[url={}]", urlStr,e);
		} catch (IOException e) {
			logger.error("I/O流出错[{}]", e.getMessage(),e);
		} finally {
			if(httpConnection != null) httpConnection.disconnect();
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
			try {
				if(outputStreamWriter != null)outputStreamWriter.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
		}
		return responseData;
	}

	private static String getURLParam(Map<String, String> param, String enc) {
		if (NullUtil.isNullOrEmpty(param)) return STRING_EMPTY;
		StringBuilder urlParam = new StringBuilder();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (key != null && value != null) {
				try {
					key = URLEncoder.encode(key, enc);
					value = URLEncoder.encode(value, enc);
				} catch (UnsupportedEncodingException e) {
					logger.error("对键值编码出错[key={},value={}]", key, value);
					continue;
				}
				urlParam.append(key).append("=").append(value);
			}
			urlParam.append(STRING_URL_LINK_CHAR);
		}
		urlParam = urlParam.deleteCharAt(urlParam
				.lastIndexOf(STRING_URL_LINK_CHAR));
		return urlParam.toString();
	}
	
	private static String getResponsData(InputStream is,String enc) throws IOException {
		StringBuilder responseData = new StringBuilder(STRING_EMPTY);
		BufferedReader br = new BufferedReader(new InputStreamReader(is, enc));
		String tempStr = null;
		while(true) {
			tempStr = br.readLine();
			if(tempStr == null) break;
			else responseData.append(tempStr);
		}
		br.close();
		return responseData.toString();
	}
}
