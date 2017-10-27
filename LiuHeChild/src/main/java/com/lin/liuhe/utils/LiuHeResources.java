package com.lin.liuhe.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.liuhe.service.RedisUtils;

public class LiuHeResources {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger logger = Logger.getLogger(LiuHeResources.class);
	private static String qi="";

	/**
	 * 对开奖记录进行资源分配获取
	 * @param redisUtils
	 * @param model
	 * @return
	 */
	public static void getKaiJiang(RedisUtils redisUtils,Model model){
		String kaijiang = redisUtils.get(GlobalString.KAIJIANG);
		if(kaijiang == null || kaijiang.length()==0){
			kaijiang = LiuHeResourcesKaiJiang.getKaiJaings();
			redisUtils.set(GlobalString.KAIJIANG, kaijiang);
		}
		try {
			kaijiang = kaijiang.substring(7,kaijiang.indexOf("t")-3);
			String[] split = kaijiang.split(",");
			String qishu = String.valueOf(redisUtils.get(GlobalString.QISHU));
			String string = split[0];
			if(!string.equals(qishu) ){
				kaijiang = LiuHeResourcesKaiJiang.getKaiJaings();
				redisUtils.set(GlobalString.KAIJIANG, kaijiang);
				split = kaijiang.split(",");
			}
			model.addAttribute("q",  new StringBuilder().append(split[0]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[0])).append(")").toString());
			model.addAttribute("w1", new StringBuilder().append(split[1]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[1])).append(")").toString());
			model.addAttribute("w2", new StringBuilder().append(split[2]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[2])).append(")").toString());
			model.addAttribute("w3", new StringBuilder().append(split[3]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[3])).append(")").toString());
			model.addAttribute("w4", new StringBuilder().append(split[4]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[4])).append(")").toString());
			model.addAttribute("w5", new StringBuilder().append(split[5]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[5])).append(")").toString());
			model.addAttribute("w6", new StringBuilder().append(split[6]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[6])).append(")").toString());
			model.addAttribute("w7", new StringBuilder().append(split[7]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[7])).append(")").toString());
			model.addAttribute("n1", new StringBuilder().append(split[8]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[8])).append(")").toString());
			model.addAttribute("n2", new StringBuilder().append(split[9]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[9])).append(")").toString());
			model.addAttribute("n3", new StringBuilder().append(split[10]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[10])).append(")").toString());
			model.addAttribute("n4", new StringBuilder().append(split[11]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[11])).append(")").toString());
			model.addAttribute("n5", new StringBuilder().append(split[12]).append("(").append(Shengxiaozhuanhuan.getShengXiao(split[12])).append(")").toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("LiuHeResources.getKaiJiang()获取开奖资源出错了");
			getKaiJiang(redisUtils,model);
		}
	}

	public static Map<String,String> getResourcesList(Map<String,String> resources,RedisUtils redis){
		long startTime = System.currentTimeMillis();
		logger.info("LiuHeResources.getResourcesList()获取数据解析开始..."+startTime);
		//每一个资源的详细信息
		Map<String,String> resourceList = new HashMap<String,String>();
		//资源的列表
		Set<Entry<String, String>> entrySet = resources.entrySet();
		//获取资源所有的url
		try {
			for (Entry<String, String> entry : entrySet) {
				//单个url
				String keyurl = entry.getKey();
				//解析得到数据
				Set<String> resourceText = getResourceText(keyurl);
				String writeValueAsString = MAPPER.writeValueAsString(resourceText);
				//详细文字数据信息
				String value = entry.getValue();
				//添加数据
				resourceList.put(writeValueAsString, value);
			}
			String temp=GlobalString.RESOURCESLIST ;
			String qishu = redis.get(GlobalString.QISHU);
			if(qi!=null && !qi.equals(qishu)){
				//上一期的资源
				String string = redis.get(temp);
				GlobalString.RESOURCESLIST =GlobalString.RESOURCESLIST+qi;
				redis.set(GlobalString.RESOURCESLIST,string,GlobalString.TIME);
			}
			//转成字符
			String writeValueAsString = MAPPER.writeValueAsString(resourceList);
			//放到redis缓存去
			GlobalString.RESOURCESLIST =temp;
			//这一期的资源
			redis.set(GlobalString.RESOURCESLIST, writeValueAsString,GlobalString.TIME);
		} catch (JsonProcessingException e) {
			logger.info("LiuHeResources.getResourcesList()放进redis缓存失败...");
			logger.error(e);
		}
		long end = System.currentTimeMillis();
		logger.info("LiuHeResources.getResourcesList()获取数据解析结束..."+(end - startTime));
		return resourceList;
	}

	private static Set<String> getResourceText(String path){

		Set<String> temp = new TreeSet<String>();
		StringBuffer sb = new StringBuffer("");
		URL url;
		try {
			url = new URL(path);
			URLConnection conn = url.openConnection();  
			conn.setReadTimeout(30000000);  
			conn.setConnectTimeout(30000000);  
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), 
					"gbk"));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			Document document = Jsoup.parse(sb.toString());
			Elements select = document.select("table");
			if(select !=null && select.size()>0){
				for (int i =0;i<select.size();i++) {
					if(i == 1){
						logger.info("获取每一个url数据开始解析1...");
						Elements element = select.get(1).select("td");
						for (int j =0;j<element.size();j++) {
							if(element!=null){
								if(j==3){
									logger.info("获取每一个url数据开始解析2..."+path);
									Element select2 = element.get(3);
									temp.add(select2.toString());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			logger.info("LiuHeResources.getResourceText()获取超时,...");
			/*ll++;
			if(ll<8) {
				return getResourceText(path);
			}*/
		}
		return temp;
	}
	/**
	 * 获取总资源的地址
	 * @return
	 */
	public static Map<String,String> getResources(RedisUtils redis){
		boolean isFlag =false;
		boolean isFlag1s =false;
		String temp =null;
		int ii=0;
		String qishu ="";
		Map<String,String> map =new HashMap<String,String>();
		List<String> list =new ArrayList<String>();
		try {
			//获取上一期的期数
			URL url = new URL("http://www.110558.com/shang.htm");
			Document document = Jsoup.parse(url, 300000);
			Element select = document.getElementById("table5805");
			Elements attribute = select.select("a");
			//System.out.println(attribute);
			for (Element element : attribute) {
				//这是资源的名称
				String text = element.text();
				if(text!=null){
					//判断是不是下一 期，是话，就直接再次获取总资源
					if(!isFlag){
						try{
							temp = redis.get(GlobalString.QISHU);
							if(!isFlag1s){
								qi=temp;
								isFlag1s=true;
							}
						}catch(Exception e){
							temp=null;
						}
						//获取上一期的期数
						if(redis != null && temp==null){
							//第一次设置期数
							qishu = isNumber(text);
							redis.set(GlobalString.QISHU, qishu);
							logger.info("上一期的期数没有，要联网获取...");
							isFlag = true;
						}
					}

					if(text.length() == 4){
						ii++;
						text = "[勇往六合]>>咸鱼翻身"+ii;
					}
				}
				//这是资源的url
				String attr = element.attr("href");
				list.add(attr);
				if(text!=null && !text.contains("[勇往六合]>>咸鱼翻身")) {
					map.put(attr, text);
				}
				if(text!=null && !text.contains("")) {
					map.put(attr, text);
				}
			}
			//资源已经存在了，不用在保存的
			//转成字符
			logger.info("资源解析成功.....LiuHeResources.getResources()");
			String writeValueAsString = MAPPER.writeValueAsString(map);
			//第一次，放到 redis缓去  60*60*24*30
			redis.set(GlobalString.RESOURCES, writeValueAsString,GlobalString.TIME);
			String writeValueAsString1 = MAPPER.writeValueAsString(list);
			redis.set(GlobalString.URLLIST, writeValueAsString1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info("LiuHeResources.getResources()获取超时,时行 再次连接...");
			return getResources(redis);
		}
		return map;
	}
	/*
	 * 转成数字 
	 */
	private static String isNumber(String temp){
		StringBuilder sb = new StringBuilder();
		char[] charArray = temp.toCharArray();
		int i = 0;
		for (char c : charArray) {
			if(Character.isDigit(c)){
				if(i ==3){
					break;
				}
				i++;
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	 * 加载规律数据
	 * @param Rs
	 */
	public static void getResourcesLaw(final RedisUtils redisUtils) {
		try {
			//一共20个循环 
			for(int i=1;i<=20;i++) {
				URL url =null;
				if(i<10) {
					url = new URL("http://www.138246.com/bbs/gl0"+i+".htm");
				}else {
					url = new URL("http://www.138246.com/bbs/gl"+i+".htm");
				}
				logger.info("数据加载规律"+i);
				final URL tempUrl=url;
				final int tempI=i;
				new Thread(() ->{ 
					getLawRes(tempUrl,tempI,redisUtils);
				} ) .start();
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			getResourcesLaw(redisUtils);
			logger.info("LiuHeResources.getResourcesLawAlls()加载超时,时行 再次连接...");
		}
	}
	public static synchronized void getLawRes(final URL tempUrl, final int tempI,final RedisUtils redisUtils) {
		try {
			StringBuilder sb = new StringBuilder();
			URLConnection conn = tempUrl.openConnection();  
			conn.setReadTimeout(30000000);  
			conn.setConnectTimeout(30000000);  
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), 
					"gbk"));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			Document document = Jsoup.parse(sb.toString());
			Element select = document.getElementById("table5");
			String law = select.toString().replaceAll("张天师", "勇往六合");
			redisUtils.set(new StringBuilder().append("law").append(tempI).toString(), law);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info("LiuHeResources.getResourcesLaw"+tempI+"()加载超时,时行 再次连接...");
		}
	}
}
