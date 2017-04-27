package com.zjapl.weixin.transfer.service;

import java.io.Serializable;
import java.util.List;

import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.transfer.vo.WeiXinTag;

/**
 * 在微信标签服务
 * 标签总数不能超过100个
 * @author yangb
 * 
 *	错误码:
 *	-1	系统繁忙
 *	45157	标签名非法，请注意不能和其他标签重名
 *	45158	标签名长度超过30个字节
 *	45058	不能修改0/1/2这三个系统默认保留的标签
 *	45057	该标签下粉丝数超过10w，不允许直接删除
 *
 */
@SuppressWarnings("rawtypes")
public interface IWeiXinTagService {

	/**
	 * 在微信上面创建一个标签
	 * @param vo 参数 vo.name: 标签名称, 不能为空,长度不能超过30个字节
	 * @return 
	 * 		WxGroupVo.wxId:标签id，由微信分配
	 * 		WxGroupVo.name:标签名
	 */
	public WeiXinResultEx<WeiXinTag> create(WeiXinTag tag);
	
	/**
	 * 获取已经创建的标签
	 * @return
	 */
	public WeiXinResultEx<List<WeiXinTag>> obtain();
	
	/**
	 * 修改标签: 
	 * 不能和已有标签重名, 否则修改失败
	 * @param vo
	 * @return
	 */
	public WeiXinResultEx update(WeiXinTag tag);
	
	/**
	 * 删除标签
	 * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。
	 * @param vo
	 * @return
	 */
	public WeiXinResultEx delete(Serializable id);
	
}
