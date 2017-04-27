package com.zjapl.weixin.dic;

/**
 * 微信字典
 * @author yangb
 */
public class WeiXinDictionary {

/*####################################### 全局  #######################################*/
	/**
	 * 应用ID
	 */
//	public static final String APP_ID = "wx432d8aebade5a186";
	public static final String APP_ID = "wx4f4707d0ed5a9332";
	
	/**
	 * 应用密匙
	 */
//	public static final String SECRET = "99acfdb1c7d3c70a1a0ee93312888a44";
	public static final String SECRET = "cbc18905da2e102db1c17035b09ddb3e";
	
	/**
	 * 凭证类型 : 默认客户端凭证
	 */
	public static final String GRANT_TYPE = "client_credential";
	
	/**
	 * 凭证类型: 认证码凭证
	 */
	public static final String WEB_GRANT_TYPE = "authorization_code";
	
	/**
	 * 令牌获取URL
	 * GET
	 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=" + GRANT_TYPE
			+ "&appid=" + APP_ID 
			+ "&secret=" + SECRET;
	
	
	/**
	 * web 令牌获取URL, 
	 */
	public static final String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
			+ APP_ID + "&secret="
					+ SECRET+ "&code={0}&grant_type=" + WEB_GRANT_TYPE;
	
	
	/**
	 * web 长效令牌
	 */
	public static final String WEB_REFRESH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
			+ APP_ID + "&grant_type="+WEB_GRANT_TYPE + "&refresh_token={0}";
	
	/**
	 * 拉取 微信授权用户 信息
	 * GET
	 */
	public static final String WEB_PULL_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN ";
	
/*#######################################  菜单 ####################################### */
	
	/**
	 * 创建菜单链接
	 * POST
	 */
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	/**
	 * 添加个性化菜单
	 * POST
	 */
	public static final String ADD_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";
	
	
	/**
	 * 删除所有菜单
	 * GET
	 */
	public static final String DELETE_MENU_ALL_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
	
	/**
	 * 删除个性化菜单
	 */
	public static final String DELETE_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token={0}";

	/**
	 * 查询菜单
	 * GET
	 */
	public static final String QUERY_MENU_ALL_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";
	
	
	
//####################################### 用户 #######################################
	/**
	 * 获取公众号关注用户列表.
	 * GET
	 */
	public static final String QUERY_ALL_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";
	
	/**
	 * 获取关注用户的详细信息.
	 * GET
	 */
	public static final String QUERY_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
	
	/**
	 * 查询标签下的用户
	 * GET
	 */
	public static final String QUERY_USER_BY_TAG = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token={0}";
	
	/**
	 * 给用户绑定标签
	 */
	public static final String BATCH_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token={0}";
	
	/**
	 * 取消为用户绑定标签
	 */
	public static final String UN_BATCH_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token={0}";
	
	/**
	 * 查询用户身上的标签
	 * POST
	 */
	public static final String QUERY_TAG_BY_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token={0}";
	
	
//####################################### 标签 #######################################
	/**
	 * 创建标签 链接
	 * POST
	 */
	public static final String CREATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token={0}";
	
	/**
	 * 获取已经创建的标签
	 */
	public static final String QUERY_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token={0}";
	
	/**
	 * 修改标签
	 */
	public static final String UPDATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token={0}";
	
	/**
	 * 删除
	 */
	public static final String DELETE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token={0}";
	
	
//####################################### 资源 #######################################
	
	/**
	 * 获取资源
	 * POST
	 */
	public static final String QUERY_RESOURCE_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={0}";
	
	/**
	 * 上传图片到微信的链接
	 */
	public static final String UPLOAD_IMG_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";
	
}
