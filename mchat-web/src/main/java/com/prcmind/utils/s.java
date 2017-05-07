package com.prcmind.utils;

import java.util.ResourceBundle;

public class s {
	public static void main(String[] args) {
		ResourceBundle resource = ResourceBundle.getBundle("mchat-config");
		String key = resource.getString("api-url");  
		System.out.println(key);
	}
}
