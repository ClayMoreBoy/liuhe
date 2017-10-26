package com.lin.liuhe.thread;

import javax.servlet.http.HttpServletRequest;
/**
 * 获得切面中要用的HttpServletRequest对象，
 * 因为这个隔着一个框架，他们在执行的同一个过程中是使用同一个线程
 * @author Administrator
 *
 */
public class MyLogAspectBindRequest {
	private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<HttpServletRequest>();
	
	public static void setLogAspectBindRequest(HttpServletRequest request){
		local.set(request);
	}

	public static void removeLogAspectBindRequest(){
		local.remove();
	}
	
	public static HttpServletRequest getLogAspectBindRequest(){
		return local.get();
	}
} 
