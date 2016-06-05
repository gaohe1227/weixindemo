package com.common;

/**
 * 
 * @author 高鹤
 * 
 * @2016年6月5日
 *
 * 	作用:access_token的工具类
 *
 */
public class AccessToken {
	private String access_token;// 获取到的凭证,微信公众号的全局唯一接口调用凭据
	private String expires_in;// 凭证失效事件

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}
