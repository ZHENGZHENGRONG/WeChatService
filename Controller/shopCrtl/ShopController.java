package com.zjapl.weixin.shop.crtl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zjapl.system.base.BaseController;

/**
 * 微信网页接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {
	
	/**
	 * 购物车
	 * 
	 * @return
	 */
	@RequestMapping("/buy")
	public ModelAndView buy(){
		ModelAndView mv = new ModelAndView("buy");
		mv.addObject("sysUser", getSysUser());
		return mv;
	}
	
	/**
	 * 个人信息页
	 * 
	 * @return
	 */
	@RequestMapping("/myinfo")
	public ModelAndView myinfo(){
		ModelAndView mv = new ModelAndView("myinfo");
		mv.addObject("sysUser", getSysUser());
		return mv;
	}
	
	
	
	/**
	 * 测试页面一
	 * 
	 * @return
	 */
	@RequestMapping("/info")
	public ModelAndView t1(){
		ModelAndView mv = new ModelAndView("info");
		mv.addObject("sysUser", getSysUser());
		return mv;
	}
	
}
