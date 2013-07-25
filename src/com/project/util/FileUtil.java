package com.project.util;

import java.io.File;

public class FileUtil {
	/**
	 * 根据指定路径，生成一个文件
	 * @param fileName
	 * @return
	 */
	public static File createFile(String fileName) {
		File file  = new File(fileName);
		if(!file.exists()) {
			File parentFile = file.getParentFile();
			parentFile.mkdirs();
		}
		return file;
	}
}
