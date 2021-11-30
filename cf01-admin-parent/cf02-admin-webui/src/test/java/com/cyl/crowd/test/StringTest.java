package com.cyl.crowd.test;

import org.junit.Test;

import com.cyl.crowd.util.CrowdUtil;

public class StringTest {
	
	@Test
	public void testMd5() {
		String source = "123123";
		String encoded = CrowdUtil.md5(source);
		System.out.println(encoded);
		
	}

}
