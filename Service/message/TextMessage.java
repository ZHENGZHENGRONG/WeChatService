package com.zjapl.weixin.service.message;

import java.util.Date;

import com.zjapl.weixin.transfer.vo.EventInfo;

/**
 * 文本消息
 * @author yangb
 *
 */
public class TextMessage {

	public static EventInfo myMoney(EventInfo event){
		EventInfo eventInfo = new EventInfo();
		eventInfo.setFromUserName(event.getToUserName());
		eventInfo.setToUserName(event.getFromUserName());
		eventInfo.setCreateTime(new Date().getTime()+"");
		eventInfo.setMsgType("text");
		eventInfo.setContent("您的余额为:1125.3  元");
		
		return eventInfo;
	}
	
	public static String myMoney(String content){
		return "您的余额为:1125.3  元";
	}
}
