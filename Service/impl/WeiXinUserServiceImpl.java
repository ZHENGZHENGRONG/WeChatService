package com.zjapl.weixin.service.transfer.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zjapl.weixin.dic.WeiXinDictionary;
import com.zjapl.weixin.helper.AccessTokenHelper;
import com.zjapl.weixin.service.base.WeiXinBaseServiceImpl;
import com.zjapl.weixin.transfer.service.IWeiXinUserService;
import com.zjapl.weixin.transfer.vo.WeiXinAccount;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.utils.HttpHelper;
import com.zjapl.weixin.utils.HttpHelper.HttpResult;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信用户接口实现
 * @author yangb
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class WeiXinUserServiceImpl extends WeiXinBaseServiceImpl implements IWeiXinUserService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public WeiXinResultEx<WeiXinAccount> obtainAll(String nextOpenid) {
		nextOpenid = nextOpenid == null ? "" : nextOpenid;
		String url = MessageFormat.format(WeiXinDictionary.QUERY_ALL_USER_URL, AccessTokenHelper.getTokenStr(),nextOpenid);
		HttpResult httpResult = HttpHelper.get(url);
		String result = httpResult.getString();
		logger.info(result);
		WeiXinAccount weiXinAccount = JSON.parseObject(result, WeiXinAccount.class);
		return new WeiXinResultEx<WeiXinAccount>().makeObjectResultEx(weiXinAccount);
	}

	@Override
	public WeiXinResultEx<UserVo> obtainUserInfo(String openid) {
		String url = MessageFormat.format(WeiXinDictionary.QUERY_USERINFO_URL, AccessTokenHelper.getTokenStr(),openid);
		HttpResult httpResult = HttpHelper.get(url);
		String result = httpResult.getString();
		logger.info(result);
		UserVo user = JSON.parseObject(result, UserVo.class);
		return new WeiXinResultEx<UserVo>().makeObjectResultEx(user);
	}

	@Override
	public WeiXinResultEx batchTag(BatchTag batch) {
		String data = JSON.toJSONString(batch);
		String result = methodPost(data, WeiXinDictionary.BATCH_TAG_URL, AccessTokenHelper.getTokenStr());
		return WeiXinResultEx.makeResultEx(result);
	}

	@Override
	public WeiXinResultEx<List<Integer>> obtainTagsByUser(String openid) {
		String data = "{\"openid\":\" " + openid + " \"}";
		TagIdList tagIdList = methodPost(TagIdList.class, data, WeiXinDictionary.QUERY_TAG_BY_USER_URL, AccessTokenHelper.getTokenStr());
		return new WeiXinResultEx<List<Integer>>().makeObjectResultEx(tagIdList.tagid_list);
	}

	@Override
	public WeiXinResultEx unBatchTag(BatchTag batch) {
		String data = JSON.toJSONString(batch);
		String result = methodPost(data, WeiXinDictionary.UN_BATCH_TAG_URL, AccessTokenHelper.getTokenStr());
		return WeiXinResultEx.makeResultEx(result);
	}

	@Override
	public WeiXinResultEx<WeiXinAccount> obtainUserByTag(Serializable tagId, String nextOpenid) {
		String url = WeiXinDictionary.QUERY_USER_BY_TAG;
		WeiXinAccount weiXinAccount = methodGet(WeiXinAccount.class, url, AccessTokenHelper.getTokenStr());
		return new WeiXinResultEx<WeiXinAccount>().makeObjectResultEx(weiXinAccount);
	}
	
	class TagIdList{
		public List<Integer> tagid_list;
	}

}
