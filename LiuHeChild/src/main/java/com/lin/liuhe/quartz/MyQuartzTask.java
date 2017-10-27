package com.lin.liuhe.quartz;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lin.liuhe.service.RedisUtils;
import com.lin.liuhe.utils.GlobalString;
import com.lin.liuhe.utils.LiuHeResources;
import com.lin.liuhe.utils.LiuHeResourcesKaiJiang;
/**
 * 1.建立石英任务类继承org.springframework.scheduling.quartz.QuartzJobBean
 * @author Rs
 *
 */
@Service // 此注解必加  
public class MyQuartzTask{
	//总资源的调度时间
		private static final String ALLRESOURCESCRONEXPRESSION="* 20,45 03,13,19,20 * * ?";
		//开奖时间的调度时间
		private static final String KAIJIANGCRONEXPRESSION="0/5 33,34,35,36,37 21 * * ?";

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyQuartzTask.class);
	@Autowired
	private  RedisUtils redisUtils;
	
	@Scheduled(cron=KAIJIANGCRONEXPRESSION)
	public void executekaijiang(){
		logger.info("开启定时加载数据开奖记录进行中.................................");
		String kaiJiang = LiuHeResourcesKaiJiang.getKaiJaings();
		logger.info("开奖记录数据保存到redis中去................................."+kaiJiang);
		redisUtils.set(GlobalString.KAIJIANG, kaiJiang);
	}
	@Scheduled(cron=ALLRESOURCESCRONEXPRESSION)
	public void executesources(){
		logger.info("开启定时加载数据,保存到redis中去.................................");
		//获取规律动态数据
		LiuHeResources.getResourcesLaw(redisUtils);
		//获取资源的总地址
		Map<String, String> resources = LiuHeResources.getResources(redisUtils);
		//得到 每个url的数据
		LiuHeResources.getResourcesList(resources,redisUtils);
	}

}
