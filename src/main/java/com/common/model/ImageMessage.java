package com.common.model;

import java.io.Serializable;

/**
 * 
 * @author �ߺ�
 * 
 * @2016��6��5��
 *
 * ����:ͼƬ��Ϣ
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
