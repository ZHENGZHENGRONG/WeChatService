package com.zjapl.weixin.transfer.service;

import java.util.Date;

import com.zjapl.weixin.transfer.vo.EventInfo;

/**
 * 微信文本消息处理
 * @author yangb
 *
 */
public abstract class WeiXinMessageAbstract implements IWeiXinMessageService {

	@Override
	public EventInfo handlerText(EventInfo req) {
		String server = req.getToUserName();
		String openid = req.getFromUserName();
		String content = req.getContent();
		
		EventInfo resp = new EventInfo();
		resp.setToUserName(openid);
		resp.setFromUserName(server);
		resp.setMsgType("text");
		resp.setContent(replyText(content));
		resp.setCreateTime(new Date().getTime()+"");
		return resp;
	}
	
	@Override
	public EventInfo handlerVoice(EventInfo req) {
		String server = req.getToUserName();
		String openid = req.getFromUserName();
		String recognition = req.getRecognition();//语音识别结果
		
		EventInfo resp = new EventInfo();
		resp.setToUserName(openid);
		resp.setFromUserName(server);
		resp.setMsgType("text");
		resp.setContent(replyVoice(recognition));
		resp.setCreateTime(new Date().getTime()+"");
		return resp;
	}
	
	/**
	 * 回复文字消息
	 * @param content
	 * @return
	 */
	public abstract String replyText(String content);
	
	/**
	 * 回复文字消息
	 * @param content
	 * @return
	 */
	public abstract String replyVoice(String content);

}
