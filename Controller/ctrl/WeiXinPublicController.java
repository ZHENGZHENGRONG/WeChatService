package com.zjapl.weixin.transfer.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjapl.weixin.transfer.service.IWeiXinEventService;
import com.zjapl.weixin.transfer.service.IWeiXinMessageService;
import com.zjapl.weixin.transfer.service.IWeiXinTokenService;
import com.zjapl.weixin.transfer.vo.EventInfo;
import com.zjapl.weixin.transfer.vo.ServerAuthInfo;
import com.zjapl.weixin.utils.AplWeiXinUtils;

/**
 * 全局接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public")
public class WeiXinPublicController {
	public static final String REQUEST_TYPE_EVENT = "event"; //事件类型
	public static final String REQUEST_TYPE_TEXT = "text";  //文本消息
	public static final String REQUEST_TYPE_VOICE = "voice";  //语音消息
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IWeiXinTokenService tokenService;
	@Autowired
	private IWeiXinEventService eventService;
	@Autowired
	private IWeiXinMessageService messageService;
	
	/**
	 * 微信服务器验证回调接口
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping(value="/global_event", method=RequestMethod.GET)
	@ResponseBody
	public String globalEvent(@Validated ServerAuthInfo serverInfo){
		
		return serverInfo.getEchostr();
	}
	
	/**
	 * 微信事件回调接口
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/global_event", method=RequestMethod.POST)
	@ResponseBody
	public String globalEvent(@RequestBody String value){
		EventInfo requestInfo = AplWeiXinUtils.xml2EventInfo(value);
		EventInfo responseInfo = null;
		switch(requestInfo.getMsgType()){
		case REQUEST_TYPE_EVENT: 
			responseInfo = eventService.handleEvent(requestInfo);
			break;
			
		case REQUEST_TYPE_TEXT: 
			responseInfo = messageService.handlerText(requestInfo);
			break;
			
		case REQUEST_TYPE_VOICE:
			responseInfo = messageService.handlerVoice(requestInfo);
			break;
		}
	
		return AplWeiXinUtils.EventInfo2Xml(responseInfo);
	}
	
	/**
	 * 获取系统的AccessToken
	 * @return
	 */
	@RequestMapping("/accessToken")
	@ResponseBody
	public String AccessToken(){
		return tokenService.obtainAccessToken();
	}
}
