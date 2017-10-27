package com.lin.liuhe.service;

import java.util.Map;

public interface HttpClientService {
	public String doGet(String url) throws Exception;
	public String doPost(String url,Map<String,String> map) throws Exception;
}
