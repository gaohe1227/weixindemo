package com.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月8日
 *
 * 作用:模板消息实体类
 */
public class TemplateMessage {
	private String touser;// 用户openid（openid是公众号的普通用户的一个唯一的标识）
	private String template_id;// 模板id
	private String url;// 跳转路径
	private Map<String, TemplateMessageData> data = new HashMap<String, TemplateMessageData>();

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, TemplateMessageData> getData() {
		return data;
	}

	public void setData(Map<String, TemplateMessageData> data) {
		this.data = data;
	}

}
