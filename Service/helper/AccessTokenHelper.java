package com.zjapl.weixin.helper;

import java.text.MessageFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.transfer.vo.AccessTokenInfo;
import com.zjapl.weixin.utils.HttpHelper;
import com.zjapl.weixin.utils.HttpHelper.HttpResult;

/**
 * AccessToken的获取帮助类
 * 由于AccessToken的获取有次数限制, 需要做成单例
 * @author yangb
 *
 */
public class AccessTokenHelper {
	/**
	 * APP 令牌
	 */
	private static AccessTokenInfo tokenInfo;

	private static AccessTokenHelper accessTokenHelper;
	
	private AccessTokenHelper(){
		initToken();
	}
	
	public static AccessTokenHelper create(){
		if(accessTokenHelper == null){
			accessTokenHelper  = new AccessTokenHelper();;
		}
		return accessTokenHelper;
	}
	
	public AccessTokenInfo getToken(){
		if(tokenInfo.getOutTime().getTime() < new Date().getTime()){ //如果过期, 重新获取Token
			initToken();
		}
		return tokenInfo;
	}
	

	public static String getTokenStr(){
		AccessTokenHelper tokenHelper = AccessTokenHelper.create();
		AccessTokenInfo token = tokenHelper.getToken();
		return token.getAccessToken();
	}
	
	public static AccessTokenInfo getWebToken(String code){
		return initWebToken(code);
	}

	private static AccessTokenInfo initWebToken(String code){
		//请求微信后台,获取token
		
		String url = MessageFormat.format(WeiXinDictionary.WEB_ACCESS_TOKEN_URL, code);
		HttpResult httpResult = HttpHelper.get(url);
		String json = httpResult.getString();/*{"access_token":"ACCESS_TOKEN","expires_in":7200}*/
		JSONObject jo = JSON.parseObject(json);
		AccessTokenInfo webToken = new AccessTokenInfo();
		webToken.setAccessToken(jo.getString("access_token"));
		webToken.setExpiresIn(jo.getString("expires_in"));
		webToken.setOpenId(jo.getString("openid"));
		webToken.setRefreshToken("refresh_token");
		webToken.setScope("scope");
		return webToken;
	}
	
	/**
	 * 初始化Token
	 */
	private void initToken() {
		//请求微信后台,获取token
		HttpResult httpResult = HttpHelper.get(WeiXinDictionary.ACCESS_TOKEN_URL);
		String json = httpResult.getString();/*{"access_token":"ACCESS_TOKEN","expires_in":7200}*/
		JSONObject jo = JSON.parseObject(json);
		
		tokenInfo = new AccessTokenInfo();
		tokenInfo.setAccessToken(jo.getString("access_token"));
		tokenInfo.setExpiresIn(jo.getString("expires_in"));
	}
	
}
