package com.common.model; 

/**
 * 
 * @author 高鹤
 * 
 * @2016年6月5日
 *
 * 作用:音乐实体类
 *
 */
public class Music {
	private String Title;// 音乐标题
	private String Description;// 音乐描述
	private String MusicUrl;// 音乐链接
	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	private String HQMusicUrl;// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String ThumbMediaId;// 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

 

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	
}