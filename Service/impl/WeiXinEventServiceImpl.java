package com.zjapl.weixin.service.transfer.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zjapl.weixin.dao.IUserDao;
import com.zjapl.weixin.dic.ParamDictionary.SubscribeCode;
import com.zjapl.weixin.entity.WxUser;
import com.zjapl.weixin.service.IUserService;
import com.zjapl.weixin.service.message.NewsMessage;
import com.zjapl.weixin.service.message.TextMessage;
import com.zjapl.weixin.transfer.service.IWeiXinUserService;
import com.zjapl.weixin.transfer.service.WeiXinEventServiceAbstract;
import com.zjapl.weixin.transfer.vo.EventInfo;
import com.zjapl.weixin.vo.UserVo;

import tk.mybatis.mapper.entity.Example;

/**
 * 用户事件处理.
 * @author yangb
 *
 */
@Service
public class WeiXinEventServiceImpl extends WeiXinEventServiceAbstract {

	@Autowired
	private IWeiXinUserService WxUserService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IUserDao userDao;
	
	@Autowired
	public void init(){
		super.userService = this.WxUserService;
	}
	
	@Override
	public EventInfo subscribe(UserVo user) {
		userService.addOrEditWeiXinUser(user);
		return null;
	}

	@Override
	public void unsubscribe(UserVo user) {  //openid
		//用户取消关注
		System.err.println(JSON.toJSONString(user));
		Example example = new Example(WxUser.class);
		example.createCriteria().andEqualTo("openId", user.getOpenId());
		WxUser wuser = new WxUser();
		wuser.setSubscribe(SubscribeCode.UNSUBSCRIBE);
		userDao.updateByExampleSelective(wuser, example);
	}
	
	@Override
	public EventInfo custom(EventInfo event) {
		
		if("login_zxzk".equals(event.getEventKey())||"unlogin_jrth".equals(event.getEventKey())){
			return NewsMessage.LastSales(event);
		}else if("login_zxyh".equals(event.getEventKey())||"unLogin_hyth".equals(event.getEventKey())){
			return NewsMessage.LastDiscount(event);
		}else if("login_yue".equals(event.getEventKey())){
			return TextMessage.myMoney(event);
		}
		
		return NewsMessage.latest(event);
	}

	@Override
	public void scan(UserVo user) {
		
	}

	


}
