package com.common;
/**
 * 
 * @author �ߺ�
 * 
 * @2016��6��5��
 *
 * ����:access_token�Ĺ�����
 *
 */
public class AccessToken {
	private String access_token;//��ȡ����ƾ֤,΢�Ź��ںŵ�ȫ��Ψһ�ӿڵ���ƾ��
	private String expires_in;//ƾ֤ʧЧ�¼�
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
