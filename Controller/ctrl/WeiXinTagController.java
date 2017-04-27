package com.zjapl.weixin.transfer.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zjapl.common.result.ResultEx;
import com.zjapl.weixin.transfer.service.IWeiXinTagService;
import com.zjapl.weixin.transfer.vo.WeiXinResultEx;
import com.zjapl.weixin.transfer.vo.WeiXinTag;

/**
 * 微信标签管理.
 * @author yangb
 *
 */
@Controller
@RequestMapping("/public/tag")
public class WeiXinTagController {

	@Autowired
	private IWeiXinTagService tagSve;
	
	/**
	 * 添加微信标签
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public ResultEx create(@Validated String name){
		WeiXinTag tag = new WeiXinTag();
		tag.setName(name);
		WeiXinResultEx<WeiXinTag> ex = tagSve.create(tag);
		System.err.println(ex.getData().getName());
		return ex;
	}
	
	/**
	 * 查询微信标签
	 * 
	 * @param server
	 * @return
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public ResultEx queryAll(){
		WeiXinResultEx<List<WeiXinTag>> ex = tagSve.obtain();
		List<WeiXinTag> data = ex.getData();
		System.err.println(JSON.toJSONString(data));
		return ex;
	}
}
