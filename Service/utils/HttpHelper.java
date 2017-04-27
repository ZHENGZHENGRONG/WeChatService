package com.zjapl.weixin.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelper {
	private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	public static final String URL = "http://127.0.0.1:8090/";

	public static String upload(String url, File file) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String result = null;
        try{  
            //把一个普通参数和文件上传给下面这个地址    是一个servlet  
            HttpPost httpPost = new HttpPost(url);  
            //把文件转换成流对象FileBody  
            FileBody bin = new FileBody(file);  
            //普通字段  重新设置了编码方式  
//            StringBody comment = new StringBody("这里是一个评论", ContentType.create("text/plain", Consts.UTF_8));  
              
              
            HttpEntity reqEntity = MultipartEntityBuilder.create()  
            .addPart("media", bin)//相当于<input type="file" name="media"/>  
//            .addPart("comment", comment)  
            .build();  
              
            httpPost.setEntity(reqEntity);  
              
            //发起请求   并返回请求的响应  
            CloseableHttpResponse response = httpClient.execute(httpPost);  
            try {  
                System.out.println("----------------------------------------");  
                //打印响应状态  
                System.out.println(response.getStatusLine());  
                //获取响应对象  
                HttpEntity resEntity = response.getEntity();  
                if (resEntity != null) {  
                    //打印响应内容  
                	result = EntityUtils.toString(resEntity,Charset.forName("UTF-8"));  
                }  
                //销毁  
                EntityUtils.consume(resEntity);  
  
            } finally {  
                response.close();  
            }  
        }finally{  
            httpClient.close();  
        }  
		return result;
	}
	
	/** get请求，获取返回字符串内容 */
	public static HttpResult get(String url) {
		
		HttpGet httpGet = new HttpGet(url);
		return execute(url, httpGet);
	}

	/** post请求，获取返回字符串内容 */
	public static HttpResult post(String url, byte[] bytes) {
		HttpPost httpPost = new HttpPost(url);
		ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
		httpPost.setEntity(byteArrayEntity);
		return execute(url, httpPost);
	}

	/** 下载 */
	public static HttpResult download(String url) {
		HttpGet httpGet = new HttpGet(url);
		return execute(url, httpGet);
	}

	/** 执行网络访问 */
	private static HttpResult execute(String url, HttpRequestBase requestBase) {
		boolean isHttps = url.startsWith("https://");//判断是否需要采用https
		AbstractHttpClient httpClient = HttpClientFactory.create(isHttps);
		HttpContext httpContext = new SyncBasicHttpContext(new BasicHttpContext());
		HttpRequestRetryHandler retryHandler = httpClient.getHttpRequestRetryHandler();//获取重试机制
		int retryCount = 0;
		boolean retry = true;
		while (retry) {
			try {
				HttpResponse response = httpClient.execute(requestBase, httpContext);//访问网络
				if (response != null) {
					return new HttpResult(response, httpClient, requestBase);
				}
			} catch (Exception e) {
				IOException ioException = new IOException(e.getMessage());
				retry = retryHandler.retryRequest(ioException, ++retryCount, httpContext);//把错误异常交给重试机制，以判断是否需要采取从事
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	/** http的返回结果的封装，可以直接从中获取返回的字符串或者流 */
	public static class HttpResult {
		private HttpResponse mResponse;
		private InputStream mIn;
		private String mStr;
		private HttpClient mHttpClient;
		private HttpRequestBase mRequestBase;

		public HttpResult(HttpResponse response, HttpClient httpClient, HttpRequestBase requestBase) {
			mResponse = response;
			mHttpClient = httpClient;
			mRequestBase = requestBase;
		}

		public int getCode() {
			StatusLine status = mResponse.getStatusLine();
			return status.getStatusCode();
		}

		/** 从结果中获取字符串，一旦获取，会自动关流，并且把字符串保存，方便下次获取 */
		public String getString() {
			if (!StringUtils.isEmpty(mStr)) {
				return mStr;
			}
			InputStream inputStream = getInputStream();
			ByteArrayOutputStream out = null;
			if (inputStream != null) {
				try {
					out = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024 * 4];
					int len = -1;
					while ((len = inputStream.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					byte[] data = out.toByteArray();
					mStr = new String(data, "utf-8");
				} catch (Exception e) {
					logger.error(e.getMessage());
				} finally {
					IOUtils.close(out);
					close();
				}
			}
			return mStr;
		}

		/** 获取流，需要使用完毕后调用close方法关闭网络连接 */
		public InputStream getInputStream() {
			if (mIn == null && getCode() < 300) {
				HttpEntity entity = mResponse.getEntity();
				try {
					mIn = entity.getContent();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			return mIn;
		}

		/** 关闭网络连接 */
		public void close() {
			if (mRequestBase != null) {
				mRequestBase.abort();
			}
			IOUtils.close(mIn);
			if (mHttpClient != null) {
				mHttpClient.getConnectionManager().closeExpiredConnections();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("F:\\workspace\\APL-WEIXIN\\weixin-service\\src\\test\\java\\t3.jpg");
		String token = "DEqD5sU03ArV9dxfyo-_TXc72hP1gu7jilff0FmQ49u0CqmTbftMGpCG4VH2-hjimoZTprnPkjSNjUtNj0GH7pnHyTUAzBgp13lx3ohgL7q3V9fptaroydls2tVEcKhyDLLbAHAVDD";
		String result = HttpHelper.upload("https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token, file);
		System.out.println(result);
	}
}
