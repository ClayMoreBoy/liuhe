package com.lin.liuhe.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.liuhe.pojo.LiuHe;
import com.lin.liuhe.service.RedisUtils;

public class Data {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Data.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();


	public  static Map<String,Set<String>> getTranData(RedisUtils redisUtils,Model model){
		//GlobalString.RESOURCESLIST
		JavaType javaType = MAPPER.getTypeFactory().constructMapType(HashMap.class, String.class, String.class);
		//在redis取出缓存的数据
		String valus = redisUtils.get(GlobalString.RESOURCESLIST);
		if(valus==null||valus.length()==0){
			Map<String, String> resources = LiuHeResources.getResources(redisUtils);
			//得到 每个url的数据
			LiuHeResources.getResourcesList(resources,redisUtils);
			valus =redisUtils.get(GlobalString.RESOURCESLIST);
		}
		//真实的临时数据  字段
		List<LiuHe> temps = new ArrayList<LiuHe>();
		//真实的临时对应数据
		Map<String,Set<String>> tempsData = new HashMap<String,Set<String>>();
		Map<Set<String>,String> sets = new HashMap<Set<String>,String>();
		try {
			Map<String,String> set = MAPPER.readValue(valus,javaType);
			Set<Entry<String, String>> entrySet2 = set.entrySet();
			for (Entry<String, String> entry : entrySet2) {
				JavaType javaType1 = MAPPER.getTypeFactory().constructParametricType(TreeSet.class, String.class);
				String key = entry.getKey();
				sets.put(MAPPER.readValue(key,javaType1), entry.getValue());
			}
			//获取封装好的每个资料对应 的数据 
			List<Entry<Set<String>, String>> entrySet = new ArrayList<Entry<Set<String>, String>>(sets.entrySet());
			//进行封装数据，传到前台
			LiuHe liuhe =null;
			for (int i=0;i<entrySet.size();i++) {
				Entry<Set<String>, String> entry = entrySet.get(i);
				//对应 的六合数据 
				Set<String> key = entry.getKey();
				if(key!=null && key.size()>0){

					//对应 的六合数据字段
					String value = entry.getValue();
					//封装对应 
					liuhe =new LiuHe();
					liuhe.setUrl(String.valueOf(i));
					liuhe.setName(value);
					temps.add(liuhe);
					//这是返回的对应数据
					tempsData.put(String.valueOf(i), key);
				}
			}
			//给前台request
			model.addAttribute(GlobalString.LIUHES, temps);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis数据转换异常Data.getTranData(）");
		}
		return tempsData;
	}

	/**
	 * 
	 * 
	 * @param redisUtils    * 获取单个疯狂规律
	 * @param model
	 */
	public static void getTranDataLaw(RedisUtils redisUtils, Model model,String id) {
		String law = redisUtils.get(new StringBuilder().append("law").append(id).toString());
		if(law == null || "".equals(law) || law.length() <10) {
			//数据库没有数据的，再次出现获取规律动态数据
			LiuHeResources.getResourcesLaw(redisUtils);
			law = redisUtils.get(new StringBuilder().append("law").append(id).toString());
		}
		model.addAttribute(GlobalString.RAWSINGLE, law);
	}
}
