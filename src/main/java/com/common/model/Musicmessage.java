package com.common.model;

import java.io.Serializable;

/**
 * 
 * @author �ߺ�
 * 
 * @2016��6��5��
 *
 * ����:������Ϣ
 *
 */
public class Musicmessage extends BaseMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1386306767909685755L;
	private Music Music;
	public Music getMusic() {
		return Music;
	}
	public void setMusic(Music music) {
		Music = music;
	}

	 
}
