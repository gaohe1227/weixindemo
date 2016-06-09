package com.weixin.user.model;

import java.util.*;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 用户流水号;
	private String id;

	// 用户名;
	private String username;

	// 最近一次登录时间;
	private Date lastlogintime;
	private String lastlogintimestring;

	// 微信唯一标识
	private String openid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastlogintimestring() {
		return lastlogintimestring;
	}

	public void setLastlogintimestring(String lastlogintimestring) {
		this.lastlogintimestring = lastlogintimestring;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}