package com.zjapl.weixin.transfer.vo;

import java.util.List;

/**
 * 图文消息回复
 * @author yangb
 *
 */
public class NewsEventInfo extends EventInfo {

	private Integer ArticleCount;
	
	private List<Item> Articles;
	
	public Integer getArticleCount() {
		return ArticleCount;
	}


	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}


	public List<Item> getArticles() {
		return Articles;
	}


	public void setArticles(List<Item> articles) {
		Articles = articles;
	}


	public static class Item{
		private String Title;
		private String Description;
		private String PicUrl;
		private String Url;
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
	}
}
