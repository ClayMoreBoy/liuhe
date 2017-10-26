package com.lin.liuhe.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.lin.liuhe.thread.MyRouterBind;

/**
 * 分布获得数据源
 * @author Rs
 *
 */
public class MyRouterData extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		//尝试读取数据信息,可能是null
		String routerBind = MyRouterBind.getRouterBind();
		//移除
		MyRouterBind.removeRouterBind();
		//返回null，也要默认的数据源
		return routerBind;
	}

}
