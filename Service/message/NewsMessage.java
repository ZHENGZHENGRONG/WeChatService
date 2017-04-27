package com.zjapl.weixin.service.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zjapl.weixin.transfer.vo.EventInfo;
import com.zjapl.weixin.transfer.vo.NewsEventInfo;
import com.zjapl.weixin.transfer.vo.NewsEventInfo.Item;

/**
 * 图文消息
 * @author yangb
 *
 */
public class NewsMessage {

	/**
	 * 最新优惠
	 * @param event
	 * @return
	 */
	public static NewsEventInfo latest(EventInfo event){
		NewsEventInfo eventInfo = new NewsEventInfo();
		eventInfo.setFromUserName(event.getToUserName());
		eventInfo.setToUserName(event.getFromUserName());
		eventInfo.setCreateTime(new Date().getTime()+"");
		eventInfo.setMsgType("news");
		
		List<Item> articles = new ArrayList<>();
		Item item1 = new Item();
		item1.setTitle("重大好消息:五一马上就要来了,你准备好了吗");
		item1.setDescription("好消息,好消息,大后天就是周末啦");
		item1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7pQ7falpafENyPPZl4zN4xaaFicEaWf4PJIPuuic0CgVIMZzLp8aRpWWfaSoCBzHAsjZgKhEdobX0XQ/0");
		item1.setUrl("https://wap.sogou.com");
		articles.add(item1);
		
		Item item2 = new Item();
		item2.setTitle("你们周末去哪里玩呢,GOGOGO");
		item2.setDescription("好消息,好消息,大后天就是周末啦");
		item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7pQ7falpafENyPPZl4zN4xalylzOpwto01qKW3VxsaCmTUIxoqQFscqjvGSdW8bPde2ibxvibLkkcQA/0");
		item2.setUrl("https://wap.sogou.com");
		articles.add(item2);
		
		Item item3 = new Item();
		item3.setTitle("Where are you going to? Follow me, let`s go.");
		item3.setDescription("好消息,好消息,大后天就是周末啦");
		item3.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7pQ7falpafENyPPZl4zN4xak7qEGFUs3SvE5bGnGyNQZdROJxibbPm7ibpncwNxqIWXo7Tyw1ZE8G1Q/0");
		item3.setUrl("https://wap.sogou.com");
		articles.add(item3);
		
		eventInfo.setArticleCount(articles.size());
		eventInfo.setArticles(articles);
		
		return eventInfo;
	}
	
	public static NewsEventInfo LastSales(EventInfo event){
		NewsEventInfo eventInfo = new NewsEventInfo();
		eventInfo.setFromUserName(event.getToUserName());
		eventInfo.setToUserName(event.getFromUserName());
		eventInfo.setCreateTime(new Date().getTime()+"");
		eventInfo.setMsgType("news");
		
		List<Item> articles = new ArrayList<>();
		Item item1 = new Item();
		item1.setTitle("开心度长假低价总动员");
		item1.setDescription("　2017淘宝天猫年货节已经结束，2017淘宝天猫全年活动时间已经开始狂欢!开淘小编今天带来看看2017淘宝天猫全年活动时间安排表。从2017淘宝天猫全年活动时间安排表中可以看到：2017淘宝天猫全年活动时间安排表中有将近上百个淘宝天猫活动。2017淘宝天猫全年活动时间安排表具体内容");
		item1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7qn6lgGKMMGia5NSicWrYFfRlEy8Syxg2kkz7BNZDYX62VibMNlTJzFCexEYZg5zxTjpe7tfYZZrKtdg/0");
		item1.setUrl("http://itapl.com/weixin-web-front/public/shop/index");
		articles.add(item1);
		
		eventInfo.setArticleCount(articles.size());
		eventInfo.setArticles(articles);
		
		return eventInfo;
	}
	
	public static NewsEventInfo LastDiscount(EventInfo event){
		NewsEventInfo eventInfo = new NewsEventInfo();
		eventInfo.setFromUserName(event.getToUserName());
		eventInfo.setToUserName(event.getFromUserName());
		eventInfo.setCreateTime(new Date().getTime()+"");
		eventInfo.setMsgType("news");
		
		List<Item> articles = new ArrayList<>();
		Item item1 = new Item();
		item1.setTitle("挑战全年最低价");
		item1.setDescription("　2017淘宝天猫年货节已经结束，2017淘宝天猫全年活动时间已经开始狂欢!开淘小编今天带来看看2017淘宝天猫全年活动时间安排表。从2017淘宝天猫全年活动时间安排表中可以看到：2017淘宝天猫全年活动时间安排表中有将近上百个淘宝天猫活动。2017淘宝天猫全年活动时间安排表具体内容");
		item1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7qn6lgGKMMGia5NSicWrYFfRlDSTbfDewKwJ4tJs9k8Zszu7uWwzHE4CGtRrJMO0cyWjuQFuv05rB6g/0");
		item1.setUrl("http://itapl.com/weixin-web-front/public/shop/index");
		articles.add(item1);
		
		Item item2 = new Item();
		item2.setTitle("手机价钱不能再低了，随便你去别处打听。");
		item2.setDescription("　2017淘宝天猫年货节已经结束，2017淘宝天猫全年活动时间已经开始狂欢!开淘小编今天带来看看2017淘宝天猫全年活动时间安排表。从2017淘宝天猫全年活动时间安排表中可以看到：2017淘宝天猫全年活动时间安排表中有将近上百个淘宝天猫活动。2017淘宝天猫全年活动时间安排表具体内容");
		item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/aUNODSoHW7qn6lgGKMMGia5NSicWrYFfRlyKn8LGc8eZ4DAqlibib5G3OoPGuqS3L1JVnVQFjTzuOmwjRLpszPQbtg/0");
		item1.setUrl("http://itapl.com/weixin-web-front/public/shop/index");
		articles.add(item2);
		
		eventInfo.setArticleCount(articles.size());
		eventInfo.setArticles(articles);
		
		return eventInfo;
	}
}
