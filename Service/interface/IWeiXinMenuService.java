package com.zjapl.weixin.transfer.service;

import java.io.Serializable;
import java.util.List;

import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.vo.WeiXinThirdMenuVo;

/**
 * 微信菜单
 * @author yangb
 *
 */
@SuppressWarnings("rawtypes")
public interface IWeiXinMenuService {

	/**
	 * 同步基础菜单到微信
	 */
	public WeiXinResultEx updateWeixinMenu();
	
	/**
	 * 同步基础菜单到微信
	 */
	public WeiXinResultEx updateWeixinMenu(String json);
	
	/**
	 * 删除所有菜单(包括个性化菜单)
	 * @return
	 */
	public WeiXinResultEx deleteAll();
	
	/**
	 * 获取已创建的所有菜单.
	 * @return
	 */
	public WeiXinResultEx<AllMenu> obtainAll();
	
	/**
	 * 添加个性化菜单
	 * @param 个性化菜单Id, 从我们的数据库中取结果.
	 * @return 菜单id. menuid
	 */
	public String addWeixinConditionalMenu(Serializable conMenuId);
	
	/**
	 * 添加个性化菜单
	 * @return 菜单id. menuid
	 */
	public String addWeixinConditionalMenu(String json);
	
	/**
	 * 删除个性化菜单
	 * @param conMenuId
	 * @return
	 */
	public WeiXinResultEx deleteConditionalMenu(Serializable menuid);
	
	class AllMenu {
		/**
		 * 基础菜单
		 */
		public WeiXinThirdMenuVo menu;
		
		/**
		 * 个性化菜单
		 */
		public List<WeiXinThirdMenuVo> conditionalmenu;
	}
}
