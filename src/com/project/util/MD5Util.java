/**
 * 
 */
package com.project.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @author dingjs
 */
public class MD5Util {
	
	public static String encodeOneCount(String str) {
		return encode(str, 1);
	}
	
	public static String encode(String str,Integer encodeCount) {
		try {
			if(encodeCount >= 1) {
				MessageDigest md =MessageDigest.getInstance("MD5");
				byte[] digestByte = md.digest(str.getBytes());
				BASE64Encoder base64 = new BASE64Encoder();
				encodeCount = encodeCount - 1;
				str = encode(base64.encode(digestByte), encodeCount);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
//		System.out.println(MD5Util.encode("djs123juanaaa",1));
//		System.out.println(MD5Util.encode("djs123juanaaa",1));
//		System.out.println(MD5Util.encode("djs123juanaaa",2));
//		System.out.println(MD5Util.encode("djs123juanaaa",2));
//		System.out.println(MD5Util.encode("djs123juanaaa",3));
//		System.out.println(MD5Util.encode("djs123juanaaa",3));
		Integer i = 0x0f;
		System.out.println(i);
		System.out.println(2 << 31);
		System.out.println(Integer.toBinaryString(2 <<31));
	}
	
}
