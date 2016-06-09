package com.common;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.common.model.TemplateMessage;
import com.common.model.TemplateMessageData;

public class WeixinUtilTest {
	public static void main(String[] args) {
		AccessToken accessToken = WeixinUtil.getAccessToken();
		createMenue(accessToken.getAccess_token());
		
		//deleteMenue(accessToken.getAccess_token());
		/*testTemplateMessage(accessToken.getAccess_token());
		System.out.println("------------------------------------------------------------------");
		createusergroup(accessToken.getAccess_token());*/
		//createMenue(accessToken.getAccess_token());
		//createMenue(accessToken.getAccess_token());
		//System.out.println("测试图片上传");
		//queryMenue(accessToken.getAccess_token());
		//System.out.println("******************************************************************************************************");
	//	deleteMenue(accessToken.getAccess_token());
		//System.out.println("******************************************************************************************************");
		//queryMenue(accessToken.getAccess_token());
		//createMenue(accessToken.getAccess_token());
		// String
		// token="J6BogYXGjP4W1Yvjce0TQ4raBJoM8DSkC4owpF1sNNAK1l3E5MSJAp3NwBp3B2VEjEst1QhoaDzA424eSoGF3kX8FvLcWBkh65x_bPOgAVZeABAQLP";
		// uploadImage(accessToken.getAccess_token());
	}

	public static String uploadImage(String accessToken) {
		String filePath = "d:/1.jpg";
		String result = null;
		try {
			result = WeixinUtil.upload(filePath, accessToken, "thumb");
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("图片上传返回值" + result);
		return result;

	}

	public static void createMenue(String accessToken) {
 
		int result = WeixinUtil.createMenu(JSONObject.toJSONString(WeixinUtil.initMenu()), accessToken);
		if (result == 0) {
			System.out.println("创建菜单成功");
		}

	}
	public static void queryMenue(String accessToken) {

		JSONObject result = WeixinUtil.queryMenu(accessToken);
		 System.out.println("\n"+result);

	}
	public static void deleteMenue(String accessToken) {

		JSONObject result = WeixinUtil.dekleteMenu(accessToken);
		 System.out.println("\n"+result);

	}
      
	
	public static void testTemplateMessage(String token){
		TemplateMessage t=new TemplateMessage();
		t.setTouser("oQQNSwilAEyVyP6yzq9057EFiWOE");
		t.setTemplate_id("UKvkOi68HThrGLFkJfQ5GsJD8ObvYpXj5Pa6CpzJdQY");
		t.setUrl("www.baidu.com");
	 
		 Map<String, TemplateMessageData> data = new HashMap<String, TemplateMessageData>();
		 data.put("t1", new TemplateMessageData("恭喜你购买成功","#173177"));
		 data.put("t2", new TemplateMessageData("巧克力","#173177"));
		 t.setData(data);
		WeixinUtil.sendTemplateMessage(t, token);//测试模板消息
	}
	
	public static void createusergroup(String token){ 
	JSONObject jsonObject=	WeixinUtil.createusergroup( token);//测试创建用户组
	System.out.println(jsonObject);
	System.out.println("查询");
	try {
		jsonObject=WeixinUtil.queryusergroup(token);
		System.out.println(jsonObject);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//测试查询用户组
	}
	
}