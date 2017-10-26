package com.lin.liuhe.controller;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.liuhe.service.RedisUtils;
import com.lin.liuhe.utils.Data;
import com.lin.liuhe.utils.GlobalString;
import com.lin.liuhe.utils.LiuHeResources;

@Controller
public class LiuheController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LiuheController.class);

	@Autowired
	RedisUtils redisUtils;

	private static Map<String, Set<String>> tranData;
	/**
	 * 获取整个资源
	 */

	@RequestMapping("liuhe")
	public String getIndex(Model model){
		//数据包装进行转换
		tranData = Data.getTranData(redisUtils, model);
		return "index";
	}  
	/**
	 * 获取单个资源
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("88888888/{id}")
	public String getData(@PathVariable("id")String id,Model model){
		//数据包装进行转换
		Set<String> dataList = tranData.get(id);
		if(dataList!=null && dataList.size() == 0){
			tranData = Data.getTranData(redisUtils, model);
		}
		model.addAttribute(GlobalString.DATALIST, dataList);
		return "list";
	}
	/**
	 * 开奖记录
	 * @return
	 */
	@RequestMapping("kaijiang")
	public String getKaiJiang(Model model){
		LiuHeResources.getKaiJiang(redisUtils, model);
		logger.info("加载开奖记录");
		return "kaijiang";
	}
	/**
	 * 属性资料
	 * @return
	 */
	@RequestMapping("ziliao")
	public String getZiLiao(){
		logger.info("加载属性资料");
		return "ziliao";
	}
	/**
	 * 疯狂规律主页
	 * @return
	 */
	@RequestMapping("lawlist")
	public String getLawList(){
		logger.info("加载疯狂规律资料");
		return "lawlist";
	}
	/**
	 * 获取单个疯狂规律
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("raw/{id}")
	public String getLawSingle(@PathVariable("id")String id,Model model){
		//数据包装进行转换
		Data.getTranDataLaw(redisUtils, model,id);
		logger.info("加载单个疯狂规律资料");
		return "law";
	}
}
