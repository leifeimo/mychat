package com.prcmind.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(long dateTime) {
		Date date = new Date(dateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	public static String DateToStr(long dateTime, String patten) {
		Date date = new Date(dateTime);
		SimpleDateFormat format = new SimpleDateFormat(patten);
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StrToDate(String str, String patten) {

		SimpleDateFormat format = new SimpleDateFormat(patten);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	public static String DateToStr(Date date, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		String str = format.format(date);
		return str;
	}
	
	
	public static void main(String[] args) {
		System.out.println(DateToStr(1493975403000L));
	}
}
