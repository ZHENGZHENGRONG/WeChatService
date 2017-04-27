package com.zjapl.weixin.service.transfer.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.helper.AccessTokenHelper;
import com.zjapl.weixin.service.base.WeiXinBaseServiceImpl;
import com.zjapl.weixin.transfer.service.IWeiXinTokenService;
import com.zjapl.weixin.transfer.vo.AccessTokenInfo;
import com.zjapl.weixin.utils.HttpCollectionHelper;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信令牌管理实现
 * @author yangb
 *
 */
@Service
public class WeiXinTokenServiceImpl extends WeiXinBaseServiceImpl implements IWeiXinTokenService {

	@Override
	public String obtainAccessToken() {
		return AccessTokenHelper.getTokenStr();
	}
	
	@Override
	public AccessTokenInfo obtainWebAccessToken(String code){
		return AccessTokenHelper.getWebToken(code);
	}

	@Override
	public UserVo obtainUserInfo(String openId, String webTokenStr) {
//		String json = methodGet(WeiXinDictionary.WEB_PULL_USERINFO_URL, webTokenStr,openId );
		
		String accessUrl = MessageFormat.format(WeiXinDictionary.WEB_PULL_USERINFO_URL,webTokenStr);
		String json = HttpCollectionHelper.get(accessUrl);
		return parseData(json);
	}
	
	public UserVo parseData(String json){
		UserVo vo = new UserVo();
		JSONObject jb = JSON.parseObject(json);
		String openid = jb.getString("openid");
		String nickname = jb.getString("nickname");
		Short sex = jb.getShort("sex");
		String language = jb.getString("language");
		String city = jb.getString("city");
		String province = jb.getString("province");
		String country = jb.getString("country");
		String headimgurl = jb.getString("headimgurl");
//		String privilege = jb.getString("privilege");
		
		vo.setOpenId(openid);
		vo.setNickName(nickname);
		vo.setSex(sex);
		vo.setLanguage(language);
		vo.setCity(city);
		vo.setProvince(province);
		vo.setCountry(country);
		vo.setHeadImgurl(headimgurl);
		return vo;
	}
	
}
