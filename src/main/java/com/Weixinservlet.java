  package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.common.MessageUtil;
import com.common.model.TextMessage;
import com.util.CheckUtil;

/**
 * Servlet implementation class Weixinservlet
 */
public class Weixinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Weixinservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	response.getWriter().append("Served at: ").append(request.getContextPath());*/
		System.out.println("微信开发平台");
		String signature=request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String timestamp=request.getParameter("timestamp");//时间戳
		String nonce=request.getParameter("nonce");//随机数
		String echostr=request.getParameter("echostr");//随机字符串
		PrintWriter writer=response.getWriter();
		System.out.println("-----------"+CheckUtil.checkSignature(signature,timestamp,nonce));
		if(CheckUtil.checkSignature(signature,timestamp,nonce)){
			writer.println(echostr);
		}else{
			writer.println("error");
		}
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		 try {
			
			Map<String,String> map=MessageUtil.xmlToMap(request);
			String toUserName=map.get("ToUserName");
			String fromUserName=map.get("FromUserName");
			String content=map.get("Content");
			String msgType=map.get("MsgType");
			 System.out.println("--------------------"+msgType);
			String resultText="";
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){//消息类型为文本
				if("1".equals(content)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
				}
				else if("2".equals(content)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());
				}else if("3".equals(content)){//回复图片消息
					resultText=	 MessageUtil.intiImageMessage(toUserName, fromUserName, "7b15x9Nal08yDczjnfmfAwfS4-1r2YwyPqjWZGNCbAlm2so_b9bivaIkVUAxIkbn");
				}
				else if("4".equals(content)){//回复图片消息
					resultText=	 MessageUtil.intiMusicMessage(toUserName, fromUserName, "cHpXTBznObL1Q2vv073PlUL55jVY_FkHXymp8tn7XY3FsAtCgEyjJjbl5-RB7wjC","http://gaohe1018.imwork.net/weixindemo/music/1.mp3");
				}
				else	if("*".equals(content)){//关键字回复
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else{
					TextMessage textMessage=new TextMessage();
					 textMessage.setMsgType("text");
					 textMessage.setContent("您发送的消息:"+content);
					 textMessage.setFromUserName(toUserName);
					 textMessage.setToUserName(fromUserName);
					 textMessage.setCreateTime(String.valueOf(new Date().getTime()));
					 resultText=MessageUtil.textMessageToXml(textMessage);				 
						
				}  
				
				 
			}else if(MessageUtil.MESSAGE_IMAGE.equals(msgType)){//图片消息
				 String picUrl=map.get("PicUrl");//图片链接（由系统生成）
				 String mediaId=map.get("MediaId");//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
				 System.out.println("图片"+picUrl+"--------------"+mediaId);
				 resultText=MessageUtil.intiImageMessage(toUserName, fromUserName, mediaId);
			}
			
			
			else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){//消息类型为事件
				String eventType=map.get("Event");//获取事件类型
				if(MessageUtil.EVENT_SUBSCRIBE.equals(eventType)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.EVENT_CLICK.equals(eventType)){//敲击事件
					String EventKey=map.get("EventKey");
					resultText=MessageUtil.initText(toUserName, fromUserName, "敲击事件:EventKey为"+EventKey);
				}else if(MessageUtil.EVENT_VIEW.equals(eventType)){//view事件
					String url = map.get("EventKey");
					resultText = MessageUtil.initText(toUserName, fromUserName, url); 
					
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					resultText = MessageUtil.initText(toUserName, fromUserName, key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				resultText = MessageUtil.initText(toUserName, fromUserName, label);
			}
			 System.out.println("---------------------------"+resultText); 
			out.print(resultText);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
				out.close(); 
		}
	}

}