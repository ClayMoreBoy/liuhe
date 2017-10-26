package com.lin.liuhe.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 生肖转换
 * @author Rs
 *
 */
public class Shengxiaozhuanhuan {
	private static Map<String,String> shengxiao = new HashMap<String,String>();
	static {
		shengxiao.put("1,01,13,25,37,49", "鸡");
		shengxiao.put("2,02,14,26,38", "猴");
		shengxiao.put("3,03,15,27,39", "羊");
		shengxiao.put("4,04,16,28,40", "马");
		shengxiao.put("5,05,17,29,41", "蛇");
		shengxiao.put("6,06,18,30,42", "龙");
		shengxiao.put("7,07,19,31,43", "兔");
		shengxiao.put("8,08,20,32,44", "虎");
		shengxiao.put("9,09,21,33,45", "牛");
		shengxiao.put("10,22,34,46", "鼠");
		shengxiao.put("11,23,35,47", "猪");
		shengxiao.put("12,24,36,48", "狗");
	}
	
	public static  String getShengXiao(String num) {
		String temp = "";
		if(num == null || "".equals(num)) {
			return temp;
		}
		Set<Entry<String, String>> sets = shengxiao.entrySet();
		l:for (Entry<String, String> sx : sets) {
			String key = sx.getKey();
			String[] split = key.split(",");
			for (String string : split) {
				if(string.equals(num)) {
					temp = sx.getValue();
					break l;
				}
			}
		}
		return temp;
	}
}
