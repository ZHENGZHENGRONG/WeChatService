package com.zjapl.weixin.transfer.vo;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 用户凭证
 * @author yangb
 *
 */
public class AccessTokenInfo {

	/**
	 * 获取到的凭证
	 */
	private String accessToken;
	/**
	 * 凭证有效时间, 单位: 秒 s
	 */
	private String expiresIn;
	/**
	 * 过期时间
	 */
	private Date outTime;
	
	/* webAccessToke 属性*/
	
	/**
	 * 刷新access_token, refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
	 */
	private String refreshToken;
	
	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	private String openId;
	
	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	private String scope;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		//计算过期时间
		setOutTime(DateUtils.addSeconds(new Date(), Integer.parseInt(expiresIn)));
	}
	
	
}
