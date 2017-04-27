package com.zjapl.weixin.transfer.service;

import java.io.File;

import com.zjapl.weixin.transfer.vo.WeiXinResultEx;

/**
 * 微信推送接口
 * 为订阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限。
 * @author yangb
 *
 */
public interface IWeiXinPushService {

	/**
	 * 上传图片到微信.
	 * 如果成功,返回图片的Url
	 * @param imgFile
	 * @return
	 */
	public String pushImg(File imgFile);

	
	/**
	 * 群发图文消息</br>
	 * 注意: 发送图文消息微信后台会进行 <strong>原创性校验</strong>.
	 * <p>
	 * 
	 *   原创性校验:</br>
	 *   	1. 当前准备群发的文章，未命中原创库中的文章，则可以群发。</br>
	 *   	2. 当前准备群发的文章，已命中原创库中的文章</br>
	 *   		2.1: 若原创作者允许转载该文章，则可以进行群发。群发时，会自动替换成原文的样式，且会自动将文章注明为转载并显示来源。</br>
	 *   		2.2: 若原创作者禁止转载该文章，则不能进行群发。</br>
	 *  </p>
	 * @param media_id 资源id
	 * @param tag_id 如果群发给指定标签的用户,请填入标签Id, 不填发送所有.
	 * @param send_ignore_reprint </br>
	 * 		当 send_ignore_reprint 参数设置为1时，该文章允许被转载</br>
	 *		当 send_ignore_reprint 参数设置为0时，该文章不允许被转载.</br>
	 * @return
	 */
	public WeiXinResultEx<Object> sendMapnewsByTag(String media_id, String tag_id, int send_ignore_reprint);
	
	
	
}
