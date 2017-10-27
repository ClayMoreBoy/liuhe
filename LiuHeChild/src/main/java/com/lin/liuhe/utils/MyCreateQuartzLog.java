package com.lin.liuhe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.lin.liuhe.service.ManagerLogService;
import com.lin.liuhe.thread.MyRouterBind;
/**
 * ioc容器的监听器
 * 通过对当前IOC容器进行打印，发现Spring的IOC容器确实是SpringMVC的IOC容器的父容器。
[1]子容器可以引用父容器中的bean
[2]父容器不能引用子容器中的bean
⑤将Spring的监听器用于创建日志表需要注意一个问题：两个IOC容器启动都会触发事件。但是建表不用建两次。
我们可以将“是否有父容器”作为判断依据。

 * @author Rs
 *
 */
public class MyCreateQuartzLog implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	ManagerLogService managerLogService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//当系统运行的时候，就生成一个表，也提前建立后面的三个月的表，也是害怕当你指定的时间里发生一些小故障，就不会导致建立表失败,这是当前的ioc
		ApplicationContext applicationContext = event.getApplicationContext();
		//获得是否有父类
		ApplicationContext parent = applicationContext.getParent();
		//因为这个项目有两个ioc容器，只有springmvc才是子容器，父容器就是ioc
		if(parent !=null){
			//这是一启动就自动建立开始的表和后三个月的表
			String createTableLog0 = QuarthCreateTableLogUtils.createTableLog(0);
			//获得分布数据源
			MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
			//执行建立表
			managerLogService.createTableLog(createTableLog0);
			
			String createTableLog1 = QuarthCreateTableLogUtils.createTableLog(1);
			//获得分布数据源
			MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
			//执行建立表
			managerLogService.createTableLog(createTableLog1);
			
			String createTableLog2 = QuarthCreateTableLogUtils.createTableLog(2);
			//获得分布数据源
			MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
			//执行建立表
			managerLogService.createTableLog(createTableLog2);
			
			String createTableLog3 = QuarthCreateTableLogUtils.createTableLog(3);
			//获得分布数据源
			MyRouterBind.setRouterBind(GlobalString.LOG_DATASOURCE_KEY);
			//执行建立表
			managerLogService.createTableLog(createTableLog3);
		}
	}


}
