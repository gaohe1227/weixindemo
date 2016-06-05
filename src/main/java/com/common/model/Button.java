package com.common.model;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月5日
 *
 * 作用:菜单按钮实体类
 */
public class Button {
	private String type;// 菜单的响应动作类型
	private String name;//菜单标题，不超过16个字节，子菜单不超过40个字节
	
	private Button[] sub_button;//二级菜单数组，个数应为1~5个

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
