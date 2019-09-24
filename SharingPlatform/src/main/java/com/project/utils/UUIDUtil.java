package com.project.utils;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * 带‘-’的UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}
	
	/**
	 * 32位UUID
	 * @return
	 */
	public static String getUUID32(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	public static void main(String[] args) {
		
		for(int i=0 ; i < 2 ; i++){
			System.out.println(UUIDUtil.getUUID32());
		}
		
	}
}
