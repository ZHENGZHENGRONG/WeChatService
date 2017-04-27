package com.zjapl.weixin.transfer.service;

import com.zjapl.weixin.dic.WeiXinEventDict;
import com.zjapl.weixin.transfer.vo.EventInfo;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信事件接口抽象.
 * @author yangb
 *
 */
public abstract class WeiXinEventServiceAbstract implements IWeiXinEventService {

	protected IWeiXinUserService userService;
	private EventInfo event;

	@Override
	public EventInfo handleEvent(EventInfo event) {
		this.event = event;
		EventInfo newEvent = null;
		String openid = event.getFromUserName();
		UserVo user = userService.obtainUserInfo(openid).getData();
		switch(event.getEvent()){
		case WeiXinEventDict.SUBSCRIBE:
			newEvent = subscribe(user);
			break;

		case WeiXinEventDict.UNSUBSCRIBE:
			unsubscribe(user);
			break;

		case WeiXinEventDict.SCAN:
			scan(user);
			break;

		default:
			newEvent = custom(event);
			break;
		}
		
		return newEvent;
	}

	/**
	 * 用户关注
	 * @param requestInfo
	 */
	public abstract EventInfo subscribe(UserVo user);

	/**
	 * 用户取消关注
	 * @param requestInfo
	 */
	public abstract void unsubscribe(UserVo user);

	/**
	 * 已关注用户扫描公众号二维码
	 * @param requestInfo
	 */
	public abstract void scan(UserVo user);

	/**
	 * 自定义事件.
	 * @param event
	 */
	public abstract EventInfo custom(EventInfo event);
	
	/**
	 * 获取事件信息
	 * @return
	 */
	public EventInfo getEventInfo(){
		return this.event;
	}
	
	
}
