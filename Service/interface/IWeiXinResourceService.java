package com.zjapl.weixin.transfer.service;

import java.io.File;

import com.zjapl.weixin.transfer.vo.WeiXinResCollection;
import com.zjapl.weixin.transfer.vo.WeiXinResItem;
import com.zjapl.weixin.transfer.vo.WeiXinResNewsItem;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;

/**
 * 微信素材管理
 * @author yangb
 *
 */
public interface IWeiXinResourceService {

	/**
	 * 图片资源类型
	 */
	public static final String RES_TYPE_IMAGE = "image";
	/**
	 * 视频类型
	 */
	public static final String RES_TYPE_VIDEO = "video";
	/**
	 * 语音
	 */
	public static final String RES_TYPE_VOICE = "voice";
	/**
	 * 图文
	 */
	public static final String RES_TYPE_NEWS = "news";
	
	/**
	 * 获取图文消息资源
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return
	 */
	public WeiXinResultEx<WeiXinResCollection<WeiXinResNewsItem>> obtainResNews(int offset, int count);
	
	/**
	 * 获取其他消息资源
	 * @param 素材的类型 RES_TYPE_IMAGE RES_TYPE_VIDEO RES_TYPE_VOICE
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return
	 */
	public WeiXinResultEx<WeiXinResCollection<WeiXinResItem>> obtainRes(String type, int offset, int count);
 	
	
	/**
	 * 将图片文件上传到微信
	 * @param file
	 * @return
	 */
	public WeiXinResultEx<String> uploadImg(File file);
}
