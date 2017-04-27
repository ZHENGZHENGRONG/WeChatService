package com.zjapl.weixin.service.transfer.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.helper.AccessTokenHelper;
import com.zjapl.weixin.service.base.WeiXinBaseServiceImpl;
import com.zjapl.weixin.transfer.service.IWeiXinMenuService;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.utils.HttpHelper;
import com.zjapl.weixin.utils.HttpHelper.HttpResult;

/**
 * 微信菜接口实现
 * @author yangb
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class WeiXinMenuServiceImpl extends WeiXinBaseServiceImpl implements IWeiXinMenuService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public WeiXinResultEx updateWeixinMenu(String json) {
		String result = null;
		try {
			HttpResult post = HttpHelper.post(WeiXinDictionary.CREATE_MENU_URL + AccessTokenHelper.getTokenStr() , json.getBytes("utf-8"));
			result = post.getString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return WeiXinResultEx.makeResultEx(result);
	}

	@Override
	public String addWeixinConditionalMenu(String json) {
		String result = null;
		try {
			HttpResult post = HttpHelper.post(WeiXinDictionary.ADD_CONDITIONAL_MENU_URL + AccessTokenHelper.getTokenStr() , json.getBytes("utf-8"));
			result = post.getString();
			logger.info(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public WeiXinResultEx updateWeixinMenu()  {
		String result = null;
		try {
			HttpResult post = HttpHelper.post(WeiXinDictionary.CREATE_MENU_URL + AccessTokenHelper.getTokenStr() , getMenuData().getBytes("utf-8"));
			result = post.getString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return WeiXinResultEx.makeResultEx(result);
	}
	
	@Override
	public WeiXinResultEx<AllMenu> obtainAll() {
		AllMenu allMenu = methodGet(AllMenu.class, WeiXinDictionary.QUERY_MENU_ALL_URL, AccessTokenHelper.getTokenStr());
		return new WeiXinResultEx<AllMenu>().makeObjectResultEx(allMenu);
	}

	@Override
	public String addWeixinConditionalMenu(Serializable conMenuId) {
		String result = null;
		try {
			HttpResult post = HttpHelper.post(WeiXinDictionary.ADD_CONDITIONAL_MENU_URL + AccessTokenHelper.getTokenStr() , getConditionalMenuData(conMenuId).getBytes("utf-8"));
			result = post.getString();
			logger.info(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public WeiXinResultEx deleteAll() {
		HttpResult httpResult = HttpHelper.get(WeiXinDictionary.DELETE_MENU_ALL_URL + AccessTokenHelper.getTokenStr() );
		return WeiXinResultEx.makeResultEx(httpResult.getString());
	}


	
	@SuppressWarnings("static-access")
	@Override
	public WeiXinResultEx deleteConditionalMenu(Serializable menuid) {
		String data = "{\"menuid\":\" + " + menuid + " + \"}";
		String result = methodPost(data, WeiXinDictionary.DELETE_CONDITIONAL_MENU_URL, AccessTokenHelper.getTokenStr());
		return new WeiXinResultEx().makeResultEx(result);
	}
	
	/**
	 * 获取个性化菜单数据
	 * @return
	 */
	public String getConditionalMenuData(Serializable conMenuId){
		
		return "{    \"button\": [{\"key\": \"V1001_TODAY_MUSIC\",            \"name\": \"今日歌曲\",            \"type\": \"click\"        },        {            \"name\": \"菜单\",            \"sub_button\": [                {                    \"name\": \"搜索\",                    \"type\": \"view\",                    \"url\": \"http://www.soso.com/\"                },                {                    \"key\": \"V1001_GOOD\",                    \"name\": \"赞一下我们\",                    \"type\": \"click\"                }            ]        }    ],    \"matchrule\": {        \"city\": \"广州\",        \"client_platform_type\": \"2\",        \"country\": \"中国\",        \"language\": \"zh_CN\",        \"province\": \"广东\",        \"sex\": \"1\",        \"tag_id\": \"2\"    }}";
				
				
	}

	/**
	 * 获取菜单数据.
	 * @return
	 */
	public String getMenuData(){
		return "{    \"button\": "
				+ "[ "
				+ "       {"
				+ "            \"type\": \"click\",             \"name\": \"安普利测试\",             \"key\": \"V1001_TODAY_MUSIC\"        },    "
				+ "     {            \"name\": \"菜单\",             \"sub_button\": [           "
				+ "     {                    \"type\": \"view\",                     \"name\": \"搜索\",                     \"url\": \"http://www.soso.com/\"                },                 {                    \"type\": \"click\",                     \"name\": \"赞一下我们\",                     \"key\": \"V1001_GOOD\"                }            ]        }    ]}";
	}








}
				
				
				
				
				
				
				
				
				