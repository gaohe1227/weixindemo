package com.common.model;
/**
 * 
 * @author �ߺ�
 * 
 * @2016��6��5��
 *
 * ����:΢����Ϣ��
 *
 */
public abstract class BaseMessage {
	private String ToUserName;//������΢�ź�
	private String FromUserName;//���ͷ��ʺţ�һ��OpenID��
	private String CreateTime	;//��Ϣ����ʱ�� �����ͣ�
	private String MsgType;//��Ϣ����
	//private String Content;//�ı���Ϣ����
	private String MsgId;//��Ϣid��64λ����
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
