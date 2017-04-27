package com.zjapl.weixin.transfer.service;

import java.io.Serializable;
import java.util.List;

import com.zjapl.weixin.transfer.vo.WeiXinAccount;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.vo.UserVo;

/**
 * 微信用户接口
 * @author yangb
 *
 */
@SuppressWarnings("rawtypes")
public interface IWeiXinUserService {

	/**
	 * 获取当前公众号的信息
	 * 当公众号关注者数量超过10000时，可通过填写next_openid的值，从而多次拉取列表的方式来满足需求。
	 * 具体而言，就是在调用接口时，将上一次调用得到的返回中的next_openid值，作为下一次调用中的next_openid值。
	 * 空串表示从头开始拉取
	 * @return 
	 */
	public WeiXinResultEx<WeiXinAccount> obtainAll(String nextOpenid);
	
	
	/**
	 * 根据用户的openid 获取用户的详细信息.
	 * @param openid
	 * @return
	 */
	public WeiXinResultEx<UserVo> obtainUserInfo(String openid);
	
	/**
	 * 获取标签下面的用户 
	 * 
	   -1	系统繁忙
		40003	传入非法的openid
		45159	非法的tag_id
	 * @param tagId
	 * @param nextOpenid 为空串,表示从标签下面的第一位拉起.
	 * @return
	 */
	public WeiXinResultEx<WeiXinAccount> obtainUserByTag(Serializable tagId, String nextOpenid);
	
	/**
	 * 获取用户身上的标签列表
	 *  -1	系统繁忙
		40003	传入非法的openid
		49003	传入的openid不属于此AppID
	 * @param openid
	 * @return
	 */
	public WeiXinResultEx<List<Integer>> obtainTagsByUser(String openid);
	
	
	/**
	 * 批量为用户打标签
	 * 标签功能目前支持公众号为用户打上最多20个标签。
	 * 
	 * 错误码说明:
	  	-1	系统繁忙
		40032	每次传入的openid列表个数不能超过50个
		45159	非法的标签
		45059	有粉丝身上的标签数已经超过限制，即超过20个
		40003	传入非法的openid
		49003	传入的openid不属于此AppID
	 * @return
	 */
	public WeiXinResultEx batchTag(BatchTag batch);
	
	/**
	 * 批量为用户取消标签
	 * @param batch
	 * @return
	 */
	public WeiXinResultEx unBatchTag(BatchTag batch);
	
	public static class BatchTag{
		/**
		 * 用户openid集合
		 */
		public List<String> openid_list;
		/**
		 * 标签Id
		 */
		public Integer tagid;
	}
}
