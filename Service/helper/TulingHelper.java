package com.zjapl.weixin.helper;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjapl.weixin.utils.HttpHelper;

/**
 * 图灵机器人 帮助类
 * @author yangb
 *
 */
public class TulingHelper {

	private static final String API_URL = "http://www.tuling123.com/openapi/api";
	
	private static final String KEY = "1a5abf341652465ba8883bf9ab12b2fb";
	
	private static final String LOC = "浙江省杭州市";
	
	public static String execute(String content) throws Exception{
		AskMsg msgEntity = new AskMsg(KEY,content,LOC);
		String reqJson = JSON.toJSONString(msgEntity);
		String respJson = HttpHelper.post(API_URL, reqJson.getBytes("utf-8")).getString();
		
		ReplyMsg replyMsg = JSON.parseObject(respJson, ReplyMsg.class);
		return replyMsg.getText();
	}
	
	public static void main(String[] args) throws Exception {
		String str = TulingHelper.execute("你好");
		System.out.println(str);
	}
	
	static class ReplyMsg{
		String code;
		String text;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	
	static class AskMsg{
		private String key;
		private String info;
		private String loc;
		private String userid;
		
		public AskMsg() {
		}
		public AskMsg(String key, String info, String loc) {
			this.key = key;
			this.info = info;
			this.loc = loc;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		
	}
	
}


