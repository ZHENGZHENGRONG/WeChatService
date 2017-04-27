package com.zjapl.weixin.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.lf5.util.StreamUtils;

public class HttpCollectionHelper {

	public static String get(String accessUrl){
		String json = null;
		try {
			URL url = new URL(accessUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
			//3.设置HttpUrlConnection对象的一些参数，请求方式，连接的超时时间
			openConnection.setRequestMethod("GET");
			openConnection.setConnectTimeout(10*1000);
			
			int code = openConnection.getResponseCode();
			if(code == 200){
				InputStream inputStream = openConnection.getInputStream();
				//5.获取网络链接的读取流信息，将流转换成字符串。 ByteArrayOutputStream
				byte[] bytes = StreamUtils.getBytes(inputStream);
				String result = new String(bytes,"utf-8");
				json = result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
