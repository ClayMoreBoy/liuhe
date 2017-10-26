package com.lin.liuhe.router;

import org.apache.log4j.Logger;

import com.lin.liuhe.service.HttpClientService;
import com.lin.liuhe.service.RedisUtils;
import com.lin.liuhe.utils.GlobalString;
import com.lin.liuhe.utils.LiuHeResourcesKaiJiang;
/**
 * 1.建立石英任务类继承org.springframework.scheduling.quartz.QuartzJobBean
 * @author Rs
 *
 */
public class MyQuartzTaskKaiJiang{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyQuartzTaskKaiJiang.class);

	private HttpClientService httpClientService;

	private RedisUtils redisUtils;



	public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
		
	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	protected void executeInternal() {

		logger.info("开启定时加载数据开奖记录进行中.................................");
		String kaiJiang = LiuHeResourcesKaiJiang.getKaiJaings();
		logger.info("开奖记录数据保存到redis中去................................."+kaiJiang);
		redisUtils.set(GlobalString.KAIJIANG, kaiJiang);
	}


}
