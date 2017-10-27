package com.lin.liuhe.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 生成日志表
 * @author Rs
 *
 */
public class QuarthCreateTableLogUtils {
	public static String createTableLog(Integer i){
		//得到日历
		Calendar calendar =Calendar.getInstance();
		//使用偏移量
		calendar.set(Calendar.MONTH, i);
		//得到偏移量后的数据时间
		Date time = calendar.getTime();
		//格式化
		String log = new SimpleDateFormat("yyyy_MM").format(time);
		//得到以后生成的表名
		return GlobalString.MANAGER_LOG+log;
	}
}
