package com.zjapl.weixin.transfer.vo;

import com.alibaba.fastjson.JSON;
import com.zjapl.common.result.ObjectResultEx;
import com.zjapl.common.result.XResult;

/**
 * 微信结果处理接口
 * @author yangb
 *
 */
public class WeiXinResultEx<T> extends ObjectResultEx<T> {
	private static final long serialVersionUID = 1L;
	/**
	 * 错误码 0:请求成功 否则失败.
	 */
	private Integer errcode;
	
	/**
	 * 错误信息
	 */
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static WeiXinResultEx makeResultEx(String result){
		if(result == null){
			return new WeiXinResultEx<Object>();
		}
		WeiXinResultEx weiXinResult = JSON.parseObject(result, WeiXinResultEx.class);
		weiXinResult.setCode(weiXinResult.getErrcode());
		weiXinResult.setMessage(weiXinResult.getErrmsg());
		
		if(weiXinResult.getErrcode() == 0){
		}
		
		return weiXinResult;
	}
	
	public WeiXinResultEx<T> makeObjectResultEx(T data){
		WeiXinResultEx<T> ex = new WeiXinResultEx<T>();
		ex.setErrcode(0);
		ex.setErrmsg("success");
		ex.setData(data);
		return ex;
	}
	
}
