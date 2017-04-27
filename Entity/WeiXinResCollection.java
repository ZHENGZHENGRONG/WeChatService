package com.zjapl.weixin.transfer.vo;

import java.util.List;

/**
 * 微信资源的集合(不包括图文消息)
 * @author yangb
 *
 * @param <T> 素材的类型
 */
public class WeiXinResCollection<T> {

	/**
	 * 该类型的素材的总数
	 */
	private Integer totalCount;
	
	/**
	 * 本次调用获取的素材的数量
	 */
	private Integer itemCount;
	
	/**
	 * 素材的集合
	 */
	private List<T> item;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public List<T> getItem() {
		return item;
	}
	public void setItem(List<T> item) {
		this.item = item;
	}
	
}
