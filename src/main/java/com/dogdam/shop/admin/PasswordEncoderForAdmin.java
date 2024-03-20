package com.dogdam.shop.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderForAdmin {

	final static private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static String encodePassword(String rawPassword) {
		return encoder.encode(rawPassword);
	}
	
	public static boolean maches(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}
	
}
