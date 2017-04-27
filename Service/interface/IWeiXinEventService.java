package com.zjapl.weixin.transfer.service;

import com.zjapl.weixin.transfer.vo.EventInfo;

/**
 * 事件响应
 * @author yangb
 *
 */
public interface IWeiXinEventService {

	/**
	 * 处理事件 
	 * @param requestInfo
	 */
	public EventInfo handleEvent(EventInfo requestInfo);
}
