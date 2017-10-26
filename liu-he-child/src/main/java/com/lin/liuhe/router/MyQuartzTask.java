package com.lin.liuhe.router;

import java.util.Map;

import org.apache.log4j.Logger;

import com.lin.liuhe.service.ManagerLogService;
import com.lin.liuhe.service.RedisUtils;
import com.lin.liuhe.utils.LiuHeResources;
/**
 * 1.建立石英任务类继承org.springframework.scheduling.quartz.QuartzJobBean
 * @author Rs
 *
 */
public class MyQuartzTask{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyQuartzTask.class);

	private  ManagerLogService managerLogService;

	private  RedisUtils redisUtils;



	public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	public void setManagerLogService(ManagerLogService managerLogService) {
		this.managerLogService = managerLogService;
	}
	protected void executeInternal() {

		logger.info("开启定时加载数据,保存到redis中去.................................");
		//获取规律动态数据
		LiuHeResources.getResourcesLaw(redisUtils);
		//获取资源的总地址
		Map<String, String> resources = LiuHeResources.getResources(redisUtils);
		//得到 每个url的数据
		LiuHeResources.getResourcesList(resources,redisUtils);
		//		//之后的三个月的日志表
		//		String createTableLog1 = QuarthCreateTableLogUtils.createTableLog(1);
		//		//获得分布数据源
		//		MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
		//		//执行建立表
		//		managerLogService.createTableLog(createTableLog1);
		//		
		//		//2
		//		String createTableLog2 = QuarthCreateTableLogUtils.createTableLog(2);
		//		//获得分布数据源
		//		MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
		//		//执行建立表
		//		managerLogService.createTableLog(createTableLog2);
		//		
		//		//3
		//		String createTableLog3 = QuarthCreateTableLogUtils.createTableLog(3);
		//		//获得分布数据源
		//		MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
		//		//执行建立表
		//		managerLogService.createTableLog(createTableLog3);
	}


}
