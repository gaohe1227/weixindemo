package com.common.model;

import java.io.Serializable;

/**
 * 
 * @author 高鹤
 * 
 * @2016年6月5日
 *
 * 作用:图片消息
 *
 */
public class ImageMessage extends BaseMessage implements Serializable{
 
	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5388550286044327530L;
 
	private Image Image;
}
