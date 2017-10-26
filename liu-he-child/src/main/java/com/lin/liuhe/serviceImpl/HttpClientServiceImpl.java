package com.lin.liuhe.serviceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lin.liuhe.service.HttpClientService;

public class HttpClientServiceImpl implements HttpClientService {
	@Autowired
	CloseableHttpClient  httpclient;
	private String exec(HttpRequestBase fun) throws Exception{
		 CloseableHttpResponse response = null;
	        try {
	            // 执行请求
	            response = httpclient.execute(fun);
	            // 判断返回状态是否为200
	            if (response.getStatusLine().getStatusCode() == 200) {
	                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
	                return content;
	            }
	        } finally {
	            if (response != null) {
	                response.close();
	            }
	            //这个不能关闭连接池
	            //httpclient.close();
	        }
		return null;
	}
	
	public String doGet(String url) throws Exception {
		  // 定义请求的参数
        URI uri = new URIBuilder(url).build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);

       return exec(httpGet);
	}

	public String doPost(String url,Map<String,String> map) throws Exception {
		   // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        // 设置post参数
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        Set<Entry<String, String>> entrySet = map.entrySet();
        for (Entry<String, String> entry : entrySet) {
        	parameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        return exec(httpPost);
	}

}
