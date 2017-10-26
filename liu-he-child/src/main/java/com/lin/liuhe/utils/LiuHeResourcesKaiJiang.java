package com.lin.liuhe.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class LiuHeResourcesKaiJiang {
	private static final Logger logger = Logger.getLogger(LiuHeResourcesKaiJiang.class);
	
	public static String getKaiJaings(){
		ArrayList<String> list = new ArrayList<String>();
	    URL url;
	    try {
	        url = new URL("http://www.889002.com/chajian/bmjg.js");
	        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), 
	        		"utf-8"));
	        String s = "";
	        while ((s = br.readLine()) != null) {
	        	s=s.replaceAll("QQ：7136995", "QQ:2962504996");
	        	list.add(s);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
			logger.info("加载开奖记录失败");
			return getKaiJaings();
	    }
		return list.get(0).toString();
	}
}
