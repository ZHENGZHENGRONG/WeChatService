package com.zjapl.weixin.transfer.service;

import com.zjapl.weixin.transfer.vo.AccessTokenInfo;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信token管理.
 * @author yangb
 *
 */
public interface IWeiXinTokenService {

	/**
	 * 获取accessToken
	 * @return
	 */
	public String obtainAccessToken();
	
	/**
	 * 获取 web accessToken
	 * @return
	 */
	public AccessTokenInfo obtainWebAccessToken(String code);
	
	/**
	 * 拉去用户的授权信息
	 * @param openId
	 * @return
	 */
	public UserVo obtainUserInfo(String openId, String webTokenStr);
}
