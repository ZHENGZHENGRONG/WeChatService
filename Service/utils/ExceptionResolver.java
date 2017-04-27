package com.zjapl.weixin.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.zjapl.common.exception.CaptchaException;

public class ExceptionResolver {

	public static String getFailureMessage(AuthenticationException ae){
		String message = null;
		if (ae instanceof CaptchaException) {
			message = "验证码不匹配!";
		} else if (ae instanceof IncorrectCredentialsException) {
			message = "用户名或密码错误！";
		} else if (ae instanceof UnknownAccountException) {
			message = "用户名不存在！";
		} else if (ae instanceof DisabledAccountException) {
			message = "用户不可用！";
		} else if (ae instanceof AuthenticationException) {
			message = "用户名或密码错误！！！";
		} else if (ae instanceof LockedAccountException) {
			message = "账户已锁定！";
		} else if (ae instanceof ExcessiveAttemptsException) {
			message = "用户名或密码错误次数过多！";
		} else {
			message = ae.getMessage();
		}
		return message;
	}
}
