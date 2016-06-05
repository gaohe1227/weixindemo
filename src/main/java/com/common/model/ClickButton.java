package com.common.model;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月5日
 *
 * 作用:click类型菜单
 */
public class ClickButton extends Button{
	private String key;// 菜单KEY值，用于消息接口推送，不超过128字节

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
