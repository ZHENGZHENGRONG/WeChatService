package com.zjapl.weixin.shop.crtl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信网页公共页面接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public/shop")
public class ShopPublicController {
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(String code, String state, HttpSession session){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
}
