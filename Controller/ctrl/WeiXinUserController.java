package com.zjapl.weixin.transfer.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjapl.common.result.ResultEx;
import com.zjapl.weixin.transfer.service.IWeiXinUserService;

/**
 * 微信用户接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public/user")
public class WeiXinUserController {

	@Autowired
	private IWeiXinUserService userService;
	
	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/obtainAll")
	@ResponseBody
	public ResultEx obtainAll(){
		return userService.obtainAll("");
	}
	
	/**
	 * 获取用户详情
	 * 
	 * @return
	 */
	@RequestMapping("/obtainUserInfo")
	@ResponseBody
	public ResultEx obtainUserByOpenid(String openid){
		return userService.obtainUserInfo(openid);
	}
}
