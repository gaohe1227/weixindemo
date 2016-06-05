package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.common.model.Image;
import com.common.model.ImageMessage;
import com.common.model.Music;
import com.common.model.Musicmessage;
import com.common.model.TextMessage;
import com.thoughtworks.xstream.XStream;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��4��
 * 
 * ����:��Ϣ��������
 *
 */
public class MessageUtil {
	public static final String MESSAGE_TEXT="text";//�ı���Ϣ
	public static final String MESSAGE_IMAGE="image";//ͼƬ��Ϣ
	public static final String MESSAGE_MUSIC="music";//������Ϣ
	public static final String MESSAGE_VOICE="voice";//������Ϣ
	public static final String MESSAGE_VEDIO="vedio";//��Ƶ��Ϣ
	public static final String MESSAGE_SHORTVIDEO="shortvideo";//С��Ƶ��Ϣ
	public static final String MESSAGE_LOCATION="location";//����λ����Ϣ
	public static final String MESSAGE_LINK="link";//������Ϣt
	public static final String MESSAGE_EVENT="event";//ʱ����Ϣ
	public static final String EVENT_SUBSCRIBE="subscribe";//ɨ���ά���¼�
	public static final String EVENT_CLICK="click";//�û��¼�
	public static final String EVENT_VIEW="VIEW";//�鿴�¼�
	/**
	 * �����������е�xmlԪ��,��xmlת��Ϊmap
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();// SAXReader��Ҫ�Á����xml�ļ�
		InputStream in = request.getInputStream();
		Document document = saxReader.read(in);// ��ȡ�ĵ�
		Element root = document.getRootElement();// ��ȡ�ĵ���Ԫ��
		List<Element> element = root.elements();// ��ȡԪ���б�;
		for (Element e : element) {
			map.put(e.getName(), e.getText());
		}
		in.close();
		return map;
	}
	/**
	 * ���ı���Ϣ����ת��Ϊxml
	 * @param testMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage testMessage) {
		 XStream xStream=new XStream();
		 xStream.alias("xml", TextMessage.class);//��xml�ĸ��ڵ��滻Ϊxml
		return xStream.toXML(testMessage);
	}
	/**
	 * �����ע�¼�
	 * @return
	 */
	public static String menuText(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("��ӭ���Ĺ�ע!,�밴�����²���:\n\n");
		buffer.append("1.���˵���\n");
		buffer.append("2.���⡣\n");
		buffer.append("�ظ�*�������˵�");
		return buffer.toString();
		
	}
	/**
	 * ���˵�
	 * @param toUserName:������
	 * @param fromUserName:�û�
	 * @param content:����
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		 TextMessage textMessage=new TextMessage();
		 textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
		 textMessage.setContent("�����͵���Ϣ:"+content);
		 textMessage.setFromUserName(toUserName);
		 textMessage.setToUserName(fromUserName);
		 textMessage.setCreateTime(String.valueOf(new Date().getTime()));
		return textMessageToXml(textMessage);				 
		 
	}
	
	public static String firstMenu(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("����΢�Ź��ںſ����Ĳ˵�һ");
		 
		return buffer.toString();
	}

	public static String secondMenu(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("����΢�Ź��ںſ����Ĳ˵���\n\n");
		buffer.append("Ľ����ѧ��");
		 
		return buffer.toString();
	}
	
	/**
	 * /**
	 * �ظ�ͼƬ��Ϣ
	 * @param picUrl:ͼƬ����
	 * @param MediaId:ͼƬ��Ϣý��id  
	 * @param toUserName:���ںſ�����
	 * @param fromUserName :�û�
	 * @return
	 */
	public static String intiImageMessage(String toUserName,String fromUserName,  String mediaId){
		Image iamge=new Image();
		iamge.setMediaId(mediaId);
		ImageMessage imageMessage=new ImageMessage();
		imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE); 
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setCreateTime(String.valueOf(new Date().getTime()));
		imageMessage.setImage(iamge);
		 XStream xStream=new XStream();
		 xStream.alias("xml", ImageMessage.class);//��xml�ĸ��ڵ��滻Ϊxml
		 return xStream.toXML(imageMessage);
	}
	/**
	 * �ظ�������Ϣ
	 * @param toUserName:���ںſ�����
	 * @param fromUserName :�û�
	 * @param thumbMediaId:����ͼid
	 * @param musicurl:����·��
	 * @return
	 */
	public static String intiMusicMessage(String toUserName, String fromUserName, String thumbMediaId,String musicurl) {
		// TODO Auto-generated method stub
		 Music music=new Music();
		 music.setDescription("�������Ե�����");
		 music.setMusicUrl(musicurl);
		 music.setHQMusicUrl(musicurl);
		 music.setThumbMediaId(thumbMediaId);
		 music.setTitle("��������");
		 
	 
		 Musicmessage musicmessage=new Musicmessage(); 
		 musicmessage.setMsgType(MessageUtil.MESSAGE_MUSIC); 
		 musicmessage.setFromUserName(toUserName);
		 musicmessage.setToUserName(fromUserName);
		 musicmessage.setCreateTime(String.valueOf(new Date().getTime()));
		 musicmessage.setMusic(music);
		 XStream xStream=new XStream();
		 xStream.alias("xml", Musicmessage.class);//��xml�ĸ��ڵ��滻Ϊxml
		 return xStream.toXML(musicmessage);
	}
}
