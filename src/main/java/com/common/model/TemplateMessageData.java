package com.common.model;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月8日
 *
 * 作用:模板消息的夹带数据
 */
public class TemplateMessageData {
	public TemplateMessageData(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}

	private String value;
	private String color;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
