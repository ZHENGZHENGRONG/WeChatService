package com.zjapl.weixin.transfer.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjapl.common.result.ResultEx;
import com.zjapl.weixin.transfer.service.IWeiXinMenuService;

/**
 * 微信菜单接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public/menu")
public class WeiXinMenuController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IWeiXinMenuService meunService;
	
	/**
	 * 同步微信基本菜单
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping("/syncMenu")
	@ResponseBody
	public ResultEx syncMenu(){
		return meunService.updateWeixinMenu();
	}
	
	/**
	 * 添加个性化菜单
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping("/addConditionalMenu")
	@ResponseBody
	public String addConditionalMenu(){
		return meunService.addWeixinConditionalMenu(1);
	}
	
	/**
	 * 删除所有菜单
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping("/deleteAll")
	@ResponseBody
	public ResultEx deleteAll(){
		return meunService.deleteAll();
	}
}
