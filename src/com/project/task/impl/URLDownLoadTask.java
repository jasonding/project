package com.project.task.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.constant.WebConstant;
import com.project.task.ITask;
import com.project.util.FileUtil;

public class URLDownLoadTask implements ITask {
	private static Logger logger = LoggerFactory.getLogger(URLDownLoadTask.class);
	
	private long statPosition ;
	private long endPosition ;
	private String urlDownLoad;
	private String saveFile;
	public URLDownLoadTask(long statPosition, long endPosition, String urlDownLoad, String saveFile) {
		this.statPosition = statPosition;
		this.endPosition = endPosition;
		this.urlDownLoad = urlDownLoad;
		this.saveFile = saveFile;
	}
	public void doTask() {
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream is = null;
		RandomAccessFile accessFile = null;
		try {
			url = new URL(urlDownLoad);
			httpConnection = (HttpURLConnection) url.openConnection();
			//httpConnection.setRequestProperty("User-Agent", "NetFox");
			httpConnection.setRequestProperty("RANGE", "bytes=" + statPosition + "-");
			
			is = httpConnection.getInputStream();
			BufferedInputStream bs = new BufferedInputStream(is, 1024);
			
			
			File saveToFile = FileUtil.createFile(saveFile);
			accessFile = new RandomAccessFile(saveToFile, "rw");
			accessFile.seek(statPosition);
			
			byte[] bytes = new byte[1024];
			int len = 0;
			logger.info(Thread.currentThread() + "下载前" + statPosition);
			while( ( (len = bs.read(bytes)) != -1 ) && (statPosition < endPosition) ) {
				accessFile.write(bytes, 0, len);
				statPosition += len;
			}
			logger.info(Thread.currentThread() + "下载后" + statPosition);
		} catch (MalformedURLException e) {
			logger.error("组建URL出错[url={}]", urlDownLoad,e);
		}catch (Exception e) {
			//记录下载的位置
			if(statPosition > endPosition) return ;
			FileOutputStream out = null;
			String thread = Thread.currentThread().getName();
			JSONObject positionObj = new JSONObject();
			positionObj.put("startPosition", statPosition);
			positionObj.put("endPosition", endPosition);
			JSONObject obj = new JSONObject();
			obj.put(thread, positionObj);
			
			try {
				File backUpFile = FileUtil.createFile(saveFile + ".bak");
				out = new FileOutputStream(backUpFile,true);
				String jsonString = obj.toString();
				jsonString = jsonString + WebConstant.WEB_LINK_CHAR;
				out.write(jsonString.getBytes(), 0, jsonString.length());
				out.flush();
			} catch (FileNotFoundException e1) {
				logger.error("找不到对应文件异常[{}]", e.getMessage(),e);
			}catch (IOException e1) {
				logger.error("I/O流出错[{}]", e.getMessage(),e);
			}finally{
				try {
					out.close();
				} catch (IOException e1) {
					logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
				}
			}
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
			try {
				accessFile.close();
			} catch (IOException e) {
				logger.error("关闭I/O流出错[{}]", e.getMessage(),e);
			}
			if(httpConnection != null) httpConnection.disconnect();
		}
	}

	public static void setLogger(Logger logger) {
		URLDownLoadTask.logger = logger;
	}

	public void setStatPosition(long statPosition) {
		this.statPosition = statPosition;
	}

	public void setEndPosition(long endPosition) {
		this.endPosition = endPosition;
	}

	public void setUrlDownLoad(String urlDownLoad) {
		this.urlDownLoad = urlDownLoad;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
}
