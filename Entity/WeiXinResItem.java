package com.zjapl.weixin.transfer.vo;

/**
 * 资源条目 其他类型（图片、语音、视频）
 * @author yangb
 *
 */
public class WeiXinResItem {

	/**
	 * 资源Id
	 */
	private String mediaId;
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 最后更新时间
	 */
	private String updateTime;
	/**
	 * 当获取的列表是图片素材列表时，该字段是图片的URL
	 */
	private String url;
	
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
