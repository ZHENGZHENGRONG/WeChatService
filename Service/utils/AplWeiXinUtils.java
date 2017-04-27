package com.zjapl.weixin.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.thoughtworks.xstream.XStream;
import com.zjapl.weixin.transfer.vo.EventInfo;
import com.zjapl.weixin.transfer.vo.NewsEventInfo;
import com.zjapl.weixin.transfer.vo.NewsEventInfo.Item;

/**
 * 工具类
 * @author yangb
 *
 */
public class AplWeiXinUtils {

	static String str = "<xml><URL><![CDATA[http://itapl.com/weixin-web/public/global_event]]></URL><ToUserName><![CDATA[zjapl@zjapl.com]]></ToUserName><FromUserName><![CDATA[odbuCwiROygVr6zYfzLUGO9tFuHA]]></FromUserName><CreateTime>1492589427104</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[哈哈哈哈]]></Content><MsgId>1234567890123456</MsgId></xml>";
	
	/**
	 * 在Shiro中登录.
	 * @param message
	 * @param username
	 * @param password
	 * @return
	 */
	public static String shiroLogin( String username, String password) {
		UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
		String message = null;
		try {
			SecurityUtils.getSubject().login(shiroToken);
		} catch (AuthenticationException e) {
			message  = ExceptionResolver.getFailureMessage(e);
		}
		return message;
	}
	
	/**
	 * xml 转对象
	 * @param xml
	 * @return
	 */
	public static EventInfo xml2EventInfo(String xml) {
		XStream xStream = new XStream();
		
		xStream.alias("xml", EventInfo.class); 
		
		
		EventInfo event = (EventInfo) xStream.fromXML(xml);
		return event;
	}
	
	/**
	 * 对象转 xml
	 * @param event
	 * @return
	 */
	public static String EventInfo2Xml(EventInfo event) {
		if(event == null){
			return "";
		}
		XStream xStream = new XStream();
		xStream.alias("xml", EventInfo.class); 
		xStream.alias("xml", NewsEventInfo.class); 
		xStream.alias("Articles", List.class);
		xStream.alias("item", Item.class);
		
		return xStream.toXML(event);
	}
	
	
	public static void main(String[] args) {
		NewsEventInfo eventInfo = new NewsEventInfo();
		eventInfo.setFromUserName("FROM");
		eventInfo.setToUserName("TO");
		eventInfo.setMsgType("news");
		eventInfo.setCreateTime(new Date().getTime()+"");
		
		
		List<Item> articles = new ArrayList<>();
		Item item1 = new Item();
		item1.setTitle("重大好消息");
		item1.setDescription("好消息,好消息,大后天就是周末啦");
		item1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7pQ7falpafENyPPZl4zN4xafKVK4RcqbWsgYibuN8NDotYtvxRBQziaVbLZ1TruibDVy1VibbZd3odaDg/0");
		item1.setUrl("https://wap.sogou.com");
		articles.add(item1);
		
		Item item2 = new Item();
		item2.setTitle("滴答滴答滴答");
		item2.setDescription("好消息,好消息,大后天就是周末啦");
		item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7pQ7falpafENyPPZl4zN4xafKVK4RcqbWsgYibuN8NDotYtvxRBQziaVbLZ1TruibDVy1VibbZd3odaDg/0");
		item2.setUrl("https://wap.sogou.com");
		articles.add(item2);
		
		eventInfo.setArticleCount(articles.size());
		eventInfo.setArticles(articles);
		
		XStream xStream = new XStream();
		xStream.alias("xml", EventInfo.class); 
		xStream.alias("xml", NewsEventInfo.class);
		xStream.alias("Articles", List.class);
		xStream.alias("item", Item.class);
		
//		Object obj = event;
		
		EventInfo ev2 = eventInfo;
		
		String xml = xStream.toXML(ev2);
		
		
		
//		EventInfo object = (EventInfo) xStream.fromXML(str);
		System.out.println(xml);
	}
}
