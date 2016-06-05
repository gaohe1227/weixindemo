package com.common.model;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月5日
 *
 * 作用:菜单类
 */
public class Menu {
  private Button[] button;//一级菜单数组，个数应为1~3个

public Button[] getButton() {
	return button;
}

public void setButton(Button[] button) {
	this.button = button;
} 
}
