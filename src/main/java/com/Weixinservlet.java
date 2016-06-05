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
		System.out.println("΢�ſ���ƽ̨");
		String signature=request.getParameter("signature");//΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
		String timestamp=request.getParameter("timestamp");//ʱ���
		String nonce=request.getParameter("nonce");//�����
		String echostr=request.getParameter("echostr");//����ַ���
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
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){//��Ϣ����Ϊ�ı�
				if("1".equals(content)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
				}
				else if("2".equals(content)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());
				}else if("3".equals(content)){//�ظ�ͼƬ��Ϣ
					resultText=	 MessageUtil.intiImageMessage(toUserName, fromUserName, "7b15x9Nal08yDczjnfmfAwfS4-1r2YwyPqjWZGNCbAlm2so_b9bivaIkVUAxIkbn");
				}
				else if("4".equals(content)){//�ظ�ͼƬ��Ϣ
					resultText=	 MessageUtil.intiMusicMessage(toUserName, fromUserName, "cHpXTBznObL1Q2vv073PlUL55jVY_FkHXymp8tn7XY3FsAtCgEyjJjbl5-RB7wjC","http://gaohe1018.imwork.net/weixindemo/music/1.mp3");
				}
				else	if("*".equals(content)){//�ؼ��ֻظ�
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else{
					TextMessage textMessage=new TextMessage();
					 textMessage.setMsgType("text");
					 textMessage.setContent("�����͵���Ϣ:"+content);
					 textMessage.setFromUserName(toUserName);
					 textMessage.setToUserName(fromUserName);
					 textMessage.setCreateTime(String.valueOf(new Date().getTime()));
					 resultText=MessageUtil.textMessageToXml(textMessage);				 
						
				} 
				
				 System.out.println("---------------------------"+resultText);
				
				 			 
				 
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){//��Ϣ����Ϊ�¼�
				String eventType=map.get("Event");//��ȡ�¼�����
				if(MessageUtil.EVENT_SUBSCRIBE.equals(eventType)){
					resultText=	 MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}
			out.print(resultText);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
				out.close(); 
		}
	}

}
