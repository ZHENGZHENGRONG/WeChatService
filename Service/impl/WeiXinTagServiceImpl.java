package com.zjapl.weixin.service.transfer.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.helper.AccessTokenHelper;
import com.zjapl.weixin.service.base.WeiXinBaseServiceImpl;
import com.zjapl.weixin.transfer.service.IWeiXinTagService;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.transfer.vo.WeiXinTag;

@SuppressWarnings("unchecked")
@Service
public class WeiXinTagServiceImpl extends WeiXinBaseServiceImpl implements IWeiXinTagService {

	@Override
	public WeiXinResultEx<WeiXinTag> create(WeiXinTag tag) {
		Tags tags = new Tags();
		tags.setTag(tag);
		String data = JSON.toJSONString(tags);
		tags = methodPost(Tags.class,data,WeiXinDictionary.CREATE_TAG_URL, AccessTokenHelper.getTokenStr());
		if(tags.tag != null){
			return new WeiXinResultEx<WeiXinTag>().makeObjectResultEx(tags.tag);
		}
		return (WeiXinResultEx<WeiXinTag>) new WeiXinResultEx<WeiXinTag>().makeInvalidParameterResult();
	}
	
	@Override
	public WeiXinResultEx<List<WeiXinTag>> obtain() {
		String json = methodGet(WeiXinDictionary.QUERY_TAG_URL, AccessTokenHelper.getTokenStr());
 		Tags tags = JSON.parseObject(json, Tags.class);
		return new WeiXinResultEx<List<WeiXinTag>>().makeObjectResultEx(tags.getTags());
	}

	
	@Override
	public WeiXinResultEx<Object> update(WeiXinTag tag) {
		String data = JSON.toJSONString(tag);
		String result = methodPost(data, WeiXinDictionary.UPDATE_TAG_URL, AccessTokenHelper.getTokenStr());
		return WeiXinResultEx.makeResultEx(result);
	}

	@Override
	public WeiXinResultEx<Object> delete(Serializable id) {
		WeiXinTag tag = new WeiXinTag();
		tag.setId((Integer)id);
		String data = JSON.toJSONString(tag);
		String result = methodPost(data, WeiXinDictionary.DELETE_TAG_URL, AccessTokenHelper.getTokenStr());
		return WeiXinResultEx.makeResultEx(result);
	}
	
	public static class Tags{
		private List<WeiXinTag> tags;
		private WeiXinTag tag;
		public List<WeiXinTag> getTags() {
			return tags;
		}
		public void setTags(List<WeiXinTag> tags) {
			this.tags = tags;
		}
		public WeiXinTag getTag() {
			return tag;
		}
		public void setTag(WeiXinTag tag) {
			this.tag = tag;
		}
		
	}


}
