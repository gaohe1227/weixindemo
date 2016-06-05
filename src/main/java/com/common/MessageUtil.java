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
 * @author 高鹤
 *
 * 2016年6月4日
 * 
 * 作用:消息处理工具类
 *
 */
public class MessageUtil {
	public static final String MESSAGE_TEXT="text";//文本消息
	public static final String MESSAGE_IMAGE="image";//图片消息
	public static final String MESSAGE_MUSIC="music";//语音消息
	public static final String MESSAGE_VOICE="voice";//语音消息
	public static final String MESSAGE_VEDIO="vedio";//视频消息
	public static final String MESSAGE_SHORTVIDEO="shortvideo";//小视频消息
	public static final String MESSAGE_LOCATION="location";//地理位置消息
	public static final String MESSAGE_LINK="link";//链接消息t
	public static final String MESSAGE_EVENT="event";//时间消息
	public static final String EVENT_SUBSCRIBE="subscribe";//扫描二维码事件
	public static final String EVENT_CLICK="CLICK";//敲击事件
	public static final String EVENT_VIEW="VIEW";//查看事件
	public static final String MESSAGE_SCANCODE="scancode_push";
	/**
	 * 解析请求流中的xml元素,将xml转换为map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();// SAXReader主要用來解析xml文件
		InputStream in = request.getInputStream();
		Document document = saxReader.read(in);// 获取文档
		Element root = document.getRootElement();// 获取文档根元素
		List<Element> element = root.elements();// 获取元素列表;
		for (Element e : element) {
			map.put(e.getName(), e.getText());
		}
		in.close();
		return map;
	}
	/**
	 * 将文本消息对象转换为xml
	 * @param testMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage testMessage) {
		 XStream xStream=new XStream();
		 xStream.alias("xml", TextMessage.class);//将xml的根节点替换为xml
		return xStream.toXML(testMessage);
	}
	/**
	 * 处理关注事件
	 * @return
	 */
	public static String menuText(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("欢迎您的关注!,请按照以下操作:\n\n");
		buffer.append("1.主菜单；\n");
		buffer.append("2.随意。\n");
		buffer.append("回复*调出主菜单");
		return buffer.toString();
		
	}
	/**
	 * 主菜单
	 * @param toUserName:开发者
	 * @param fromUserName:用户
	 * @param content:内容
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		 TextMessage textMessage=new TextMessage();
		 textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
		 textMessage.setContent("您发送的消息:"+content);
		 textMessage.setFromUserName(toUserName);
		 textMessage.setToUserName(fromUserName);
		 textMessage.setCreateTime(String.valueOf(new Date().getTime()));
		return textMessageToXml(textMessage);				 
		 
	}
	
	public static String firstMenu(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("测试微信公众号开发的菜单一");
		 
		return buffer.toString();
	}

	public static String secondMenu(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("测试微信公众号开发的菜单二\n\n");
		buffer.append("慕课网学的");
		 
		return buffer.toString();
	}
	
	/**
	 * /**
	 * 回复图片消息
	 * @param picUrl:图片链接
	 * @param MediaId:图片消息媒体id  
	 * @param toUserName:公众号开发者
	 * @param fromUserName :用户
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
		 xStream.alias("xml", ImageMessage.class);//将xml的根节点替换为xml
		 return xStream.toXML(imageMessage);
	}
	/**
	 * 回复音乐消息
	 * @param toUserName:公众号开发者
	 * @param fromUserName :用户
	 * @param thumbMediaId:缩略图id
	 * @param musicurl:音乐路径
	 * @return
	 */
	public static String intiMusicMessage(String toUserName, String fromUserName, String thumbMediaId,String musicurl) {
		// TODO Auto-generated method stub
		 Music music=new Music();
		 music.setDescription("用来测试的音乐");
		 music.setMusicUrl(musicurl);
		 music.setHQMusicUrl(musicurl);
		 music.setThumbMediaId(thumbMediaId);
		 music.setTitle("音乐主题");
		 
	 
		 Musicmessage musicmessage=new Musicmessage(); 
		 musicmessage.setMsgType(MessageUtil.MESSAGE_MUSIC); 
		 musicmessage.setFromUserName(toUserName);
		 musicmessage.setToUserName(fromUserName);
		 musicmessage.setCreateTime(String.valueOf(new Date().getTime()));
		 musicmessage.setMusic(music);
		 XStream xStream=new XStream();
		 xStream.alias("xml", Musicmessage.class);//将xml的根节点替换为xml
		 return xStream.toXML(musicmessage);
	}
}