package com.lin.liuhe.thread;

/**
 * 这里获得分布数据源
 * 因为这个隔着一个框架，他们在执行的同一个过程中是使用同一个线程
 * @author Rs
 *
 */
public class MyRouterBind {
	private static ThreadLocal<String> local = new ThreadLocal<String>();
	
	public static void setRouterBind(String request){
		local.set(request);
	}

	public static void removeRouterBind(){
		local.remove();
	}
	
	public static String getRouterBind(){
		return local.get();
	}
} 
