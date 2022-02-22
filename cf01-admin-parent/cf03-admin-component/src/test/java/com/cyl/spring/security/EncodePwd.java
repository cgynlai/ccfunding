package com.cyl.spring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePwd {
	
	public static void main(String[] args) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String rawPwd = "123123";
		
		String encode = passwordEncoder.encode(rawPwd);
		
		System.out.println(encode);
	}

}
