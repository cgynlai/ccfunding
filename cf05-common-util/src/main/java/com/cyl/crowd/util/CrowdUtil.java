package com.cyl.crowd.util;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.cyl.crowd.constant.CrowdConstant;



public class CrowdUtil {
	
	/**
	 * ä¸“é—¨è´Ÿè´£ä¸Šä¼ æ–‡ä»¶åˆ°OSSæœ�åŠ¡å™¨çš„å·¥å…·æ–¹æ³•
	 * @param endpoint			OSSå�‚æ•°
	 * @param accessKeyId		OSSå�‚æ•°
	 * @param accessKeySecret	OSSå�‚æ•°
	 * @param inputStream		è¦�ä¸Šä¼ çš„æ–‡ä»¶çš„è¾“å…¥æµ�
	 * @param bucketName		OSSå�‚æ•°
	 * @param bucketDomain		OSSå�‚æ•°
	 * @param originalName		è¦�ä¸Šä¼ çš„æ–‡ä»¶çš„åŽŸå§‹æ–‡ä»¶å��
	 * @return	åŒ…å�«ä¸Šä¼ ç»“æžœä»¥å�Šä¸Šä¼ çš„æ–‡ä»¶åœ¨OSSä¸Šçš„è®¿é—®è·¯å¾„
	 */
	
	
	
	
	
	/**
	 * åˆ¤æ–­è¯·æ±‚ç±»åž‹æ˜¯å�¦ä¸ºajax
	 * @param request
	 * @return
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		// 1.èŽ·å�–è¯·æ±‚æ¶ˆæ�¯å¤´ä¿¡æ�¯
		String acceptInformation = request.getHeader("Accept");
		String xRequestInformation = request.getHeader("X-Requested-With");
		
		// 2.æ£€æŸ¥å¹¶è¿”å›ž
		return
		(
		acceptInformation != null
		&&
		acceptInformation.contains("application/json")
		)
		||
		(
		xRequestInformation != null
		&&
		xRequestInformation.equals("XMLHttpRequest")
		);
	}
	
	public static String md5(String source) {
		
		if(source == null || source.length() == 0) {
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		String algorithm = "md5";
		
		try {
			// use m5 to encode
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			// convert source string to byte array
			byte[] input = source.getBytes();
			// encoding
			byte[] output = messageDigest.digest(input);
			// 创建BigInteger对象存放加密后的字节数组
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum, output);
			
			// 按照16进制将bigInteger转换为字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			
			return encoded;
			
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
