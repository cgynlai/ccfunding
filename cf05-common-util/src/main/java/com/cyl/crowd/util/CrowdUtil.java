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
	
	
	
}
