package com.zjapl.weixin.service.transfer.impl;

import org.springframework.stereotype.Service;

import com.zjapl.weixin.helper.TulingHelper;
import com.zjapl.weixin.transfer.service.WeiXinTextMessageAbstract;

@Service
public class WeiXinMessageServiceImpl extends WeiXinTextMessageAbstract {

	/**
	 * 回复消息
	 * @param content
	 * @return
	 */
	public String reply(String content){
		
		try {
			return TulingHelper.execute(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "机器人死了";
	}

}
