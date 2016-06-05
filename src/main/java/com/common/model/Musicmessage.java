package com.common.model;

import java.io.Serializable;

/**
 * 
 * @author 高鹤
 * 
 * @2016年6月5日
 *
 * 作用:音乐消息
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
