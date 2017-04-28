package com.zjapl.weixin.service.transfer.impl;

import org.springframework.stereotype.Service;

import com.zjapl.weixin.helper.TulingHelper;
import com.zjapl.weixin.service.message.TextMessage;
import com.zjapl.weixin.transfer.service.WeiXinMessageAbstract;

@Service
public class WeiXinMessageServiceImpl extends WeiXinMessageAbstract {

	/**
	 * 回复消息
	 * @param content
	 * @return
	 */
	@Override
	public String replyText(String content){
		if(content.contains("余额")){
			return TextMessage.myMoney(content);
		}
		
		try {
			return TulingHelper.execute(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "机器人死了";
	}

	@Override
	public String replyVoice(String content) {
		if(content.contains("余额")){
			return TextMessage.myMoney(content);
		}
		
		try {
			return TulingHelper.execute(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "机器人死了";
	}

}
