package com.zjapl.weixin.service.transfer.impl;

import java.io.File;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.helper.AccessTokenHelper;
import com.zjapl.weixin.service.base.WeiXinBaseServiceImpl;
import com.zjapl.weixin.transfer.service.IWeiXinResourceService;
import com.zjapl.weixin.transfer.vo.WeiXinResCollection;
import com.zjapl.weixin.transfer.vo.WeiXinResItem;
import com.zjapl.weixin.transfer.vo.WeiXinResNewsItem;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.utils.HttpHelper;

/**
 * @author admin
 *
 */
@Service
public class WeiXinResourceServiceImpl extends WeiXinBaseServiceImpl implements IWeiXinResourceService {

	
	
	@Override
	public WeiXinResultEx<WeiXinResCollection<WeiXinResNewsItem>> obtainResNews(int offset, int count) {
		Data data = new Data();
		data.type = RES_TYPE_NEWS;
		data.offset = offset;
		data.count = count;
		String strData = JSON.toJSONString(data);
		String jsonResult = methodPost(strData, WeiXinDictionary.QUERY_RESOURCE_URL, AccessTokenHelper.getTokenStr());
		
		WeiXinResCollection<WeiXinResNewsItem> collection = new WeiXinResCollection<>();
		
		WeiXinResNewsItem item = null;
		
		//TODO 封装数据
		
		JSONObject jb = JSON.parseObject(jsonResult);
		Integer totalCount = jb.getInteger("total_count");
		Integer item_count = jb.getInteger("item_count");
		JSONArray ja = jb.getJSONArray("item");
		for (int i = 0; i < ja.size(); i++) {
			JSONObject jb1 = ja.getJSONObject(i);
			item = new WeiXinResNewsItem();
			jb1.getString("media_id");
			jb1.getString("update_time");
			jb1.getJSONObject("content");
		}
		
		return null;
	}

	@Override
	public WeiXinResultEx<WeiXinResCollection<WeiXinResItem>> obtainRes(String type, int offset, int count) {
		return null;
	}
	
	
	class Data{
		String type;
		int offset;
		int count;
		
	}


	@Override
	public WeiXinResultEx<String> uploadImg(File file) {
		String url = MessageFormat.format(WeiXinDictionary.UPLOAD_IMG_URL, AccessTokenHelper.getTokenStr());
		String imgUrl = null;
		try {
			String json = HttpHelper.upload(url, file);
			JSONObject jsonObject = JSON.parseObject(json);
			imgUrl = jsonObject.getString("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WeiXinResultEx<String>().makeObjectResultEx(imgUrl);
	}

}
