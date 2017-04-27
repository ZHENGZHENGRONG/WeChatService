package com.zjapl.weixin.transfer.service;

import com.zjapl.weixin.transfer.vo.EventInfo;

/**
 * 消息响应
 * @author yangb
 *
 */
public interface IWeiXinMessageService {

	/**
	 * 处理文字消息
	 * @param requestInfo
	 * @return 回复的内容
	 */
	public EventInfo handlerText(EventInfo requestInfo);
}
