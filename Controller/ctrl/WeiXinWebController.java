package com.zjapl.weixin.transfer.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zjapl.common.util.AESUtils;
import com.zjapl.system.edge.service.SysUserService;
import com.zjapl.system.entity.SysUser;
import com.zjapl.system.utils.BeanUtils;
import com.zjapl.weixin.service.IUserService;
import com.zjapl.weixin.transfer.service.IWeiXinTokenService;
import com.zjapl.weixin.transfer.vo.AccessTokenInfo;
import com.zjapl.weixin.utils.AplWeiXinUtils;
import com.zjapl.weixin.vo.QueryUserVo;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信网页接口
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public/web")
public class WeiXinWebController {

	@Autowired
	private IWeiXinTokenService tokenService;
	
	@Resource
	private IUserService wxUserService;
	
	@Resource
	private SysUserService sysUserService;

	/**
	 * 微信页面入口.
	 * @param code
	 * @param state
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/page")
	public ModelAndView page(String code, String state, HttpSession session) throws Exception{
		AccessTokenInfo token = (AccessTokenInfo) session.getAttribute("webToken");
		if(token == null){
			token = tokenService.obtainWebAccessToken(code);
			session.setAttribute("webToken", token);
		}
		
		// 查询微信用户.
		// 获取微信用户信息.
		QueryUserVo userVo = new QueryUserVo();
		userVo.setOpenId(token.getOpenId());
		UserVo wxUser = wxUserService.queryUserByOne(userVo).getData();
		//如果该用户不存在,创建之
		if(wxUser == null){
			wxUser = tokenService.obtainUserInfo(token.getOpenId(),token.getAccessToken());
			wxUserService.addOrEditWeiXinUser(wxUser);
		}else if(wxUser.getNickName() == null){
			//如果信息不完善,完善之
			UserVo newWxUser = tokenService.obtainUserInfo(token.getOpenId(),token.getAccessToken());
			BeanUtils.copyPropertiesIgnoreNullValue(newWxUser, wxUser);
			wxUserService.addOrEditWeiXinUser(wxUser);
		}
		
		if(wxUser == null || wxUser.getUserid() == null){
			//用户没有系统账户, 不能登录
			//TODO 跳转到首页
//			ModelAndView mv = new ModelAndView("index");
			ModelAndView mv = new ModelAndView("regist"); //跳转到登录页面.
			mv.addObject("openid", token.getOpenId());
			mv.addObject("state", state);
			return mv;
			
		}else{
			String message = null;
			//用户是系统用户,直接登录.
			//解析密码
			String password = wxUser.getPassword();
			password = AESUtils.aesDecrypt(password); 
			
			Long sysUserId =  wxUser.getUserid(); //获取系统用户Id
			SysUser sysUser = sysUserService.querySysUser(sysUserId).getData();//获取系统用户
			
			if(password == null){
				password = "888888";
			}
			
			message = AplWeiXinUtils.shiroLogin(sysUser.getUsername(), "888888"); //用户登录,返回登录结果,如果失败,返回失败信息,成功则为null
			
			if(message == null){
				//登录成功
				//TODO 跳转到登录成功页面
				//跳转的页面可以使用 微信带回来的参数指定响应的页面.
				ModelAndView mv = new ModelAndView(state);
				mv.addObject("openid", token.getOpenId());
				mv.addObject("sysUser", sysUser);
				return mv;
				
			}else{
				//登录失败
				//TODO 跳转到注册页面
				ModelAndView mv = new ModelAndView("regist");
				mv.addObject("openid", token.getOpenId());
				mv.addObject("message",message);
				mv.addObject("state", state);
				return mv;
			}
		}
		
	}
	
	
	/**
	 * 测试页面一
	 * 
	 * @return
	 */
	@RequestMapping("/test1")
	public ModelAndView t1(String code, String state, HttpSession session){
		
		AccessTokenInfo token = (AccessTokenInfo) session.getAttribute("WebToken");
		if(token == null){
			token = tokenService.obtainWebAccessToken(code);
			session.setAttribute("WebToken", token);
		}
		
		ModelAndView mv = new ModelAndView("test1");
		mv.addObject("token", token);
		return mv;
	}
	
	@RequestMapping("/test2")
	public ModelAndView t2(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session){
		
		AccessTokenInfo token = (AccessTokenInfo) session.getAttribute("WebToken");
		if(token == null){
			token = tokenService.obtainWebAccessToken(code);
			session.setAttribute("WebToken", token);
		}
		
		UserVo userinfo = tokenService.obtainUserInfo(token.getOpenId(),token.getAccessToken());
		
		ModelAndView mv = new ModelAndView("test2");
		mv.addObject("token", token);
		mv.addObject("userinfo", userinfo);
		return mv;
	}
	
}
