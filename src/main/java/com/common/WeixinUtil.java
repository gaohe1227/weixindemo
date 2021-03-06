package com.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.common.model.Button;
import com.common.model.ClickButton;
import com.common.model.Menu;
import com.common.model.TemplateMessage;
import com.common.model.ViewButton;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

/**
  * 
  * @author 高鹤
  * 
  * @2016年6月5日
  *
  * 作用:微信工具类
  *
  */
public class WeixinUtil {
	public static final String APPID="wxace26c73dbbcbfdc";
	public static final String APPSECRET="fc5a1e018bec46954a0147885f7cd1af";
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";//新增临时素材接口
	public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String QUERY_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String SEND_TEMPLET_MESSGAE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";//发送模板消息接口
	
	public static final String CREATE_USERGROUP_URL="https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";//创建用户分组接口
	public static final String QUERY_USERGROUP_URL="https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";//查询用户分组接口
	/**
	 * get请求
	 * @param urlStr：url路径
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject doGetStr(String urlStr) throws ClientProtocolException, IOException{
 
		  CloseableHttpClient httpclient = HttpClients.createDefault();//创建HttpClient对象
		  // 创建httpget.    
          HttpGet httpget = new HttpGet(urlStr);  
          System.out.println("executing request " + httpget.getURI());
          JSONObject jsonObject=null;
          // 执行get请求.     
			CloseableHttpResponse response = httpclient.execute(httpget);
			 // 获取响应实体    
            HttpEntity entity = response.getEntity();  
            System.out.println(response.getStatusLine());    // 打印响应状态    
            if (entity != null) {  
                // 打印响应内容长度    
                System.out.println("Response content length: " + entity.getContentLength());  
                // 打印响应内容    
                String result= EntityUtils.toString(entity,"UTF-8");
                System.out.println("Response content: " +result);
                jsonObject= JSONObject.parseObject(result); 
            }  
            
		 
		return jsonObject;
	}
	/**
	 * post请求
	 * @param urlStr：url路径
	 * @return
	 */
	public static JSONObject doPostStr(String urlStr,String outStr){
 
		  CloseableHttpClient httpclient = HttpClients.createDefault();//创建HttpClient对象
		  // 创建httppost.    
          HttpPost httpPost = new HttpPost(urlStr);  
          System.out.println("executing request " + httpPost.getURI());
          JSONObject jsonObject=null;
          // 执行post请求.    
          try {
        	  httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);// 执行post请求.
			 // 获取响应实体    
            HttpEntity entity = response.getEntity();  
            System.out.println(response.getStatusLine());    // 打印响应状态    
            if (entity != null) {  
                // 打印响应内容长度    
                System.out.println("Response content length: " + entity.getContentLength());  
                // 打印响应内容    
                String result= EntityUtils.toString(entity,"UTF-8");
                System.out.println("Response content: " +result);
                jsonObject= JSONObject.parseObject(result); 
            }   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * 获取token(即微信公众号的验证凭据)
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token = new AccessToken();
		String url = WeixinUtil.ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);// 替换路径参数
		try {
			JSONObject jsonObject = doGetStr(url);// 发起get请求
			if (null != jsonObject) {
				token.setAccess_token(jsonObject.getString("access_token"));
				token.setExpires_in(jsonObject.getString("expires_in"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	/**
	 * post请求文件上传
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//链接url
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); //设置提交方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获取输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//将文件以流的方式推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//设置结束线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的相应结果
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(!"image".equals(type)){
			typeName = type + "_media_id";
		}
		
		System.out.println(jsonObj.getString("type")+"----------------------"+jsonObj.getString("media_id")+"----------------------"+jsonObj.getString("created_at"));
		String mediaId = jsonObj.getString(typeName);//媒体文件上传后，获取时的唯一标识
		return mediaId;
	}

	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://gaohe1018.imwork.net/weixindemo/test");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button11,button21,button});
		return menu;
	}
	 /**
	  * 创建菜单
	  * @param menu:菜单xml
	  * @param token:token值
	  * @return
	  */
	public static int createMenu(String menu,String token){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);// 发送post请求
		if (jsonObject != null) {
			result = jsonObject.getIntValue("errcode");
		}
		return result;

	}
	 /**
	  * 查询菜单
	  * @param token:token
	  * @return
	  */
	public static JSONObject queryMenu(String token){
		String url=QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		try {
			JSONObject jsonObject=doGetStr(url);
			return jsonObject;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 /**
	  * 删除菜单
	  * @param token:token
	  * @return
	  */
	public static JSONObject dekleteMenu(String token){
		String url=DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		try {
			JSONObject jsonObject=doGetStr(url);
			return jsonObject;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 发送模板消息
	 * @param templateMessage:消息
	 * @param token:token
	 * @return
	 */
	public static JSONObject sendTemplateMessage(TemplateMessage templateMessage,String token){ 
		  
		  
		  CloseableHttpClient httpclient = HttpClients.createDefault();//创建HttpClient对象
		  String url=SEND_TEMPLET_MESSGAE_URL.replace("ACCESS_TOKEN", token);
		  // 创建httppost.    
          HttpPost httpPost = new HttpPost(url);  
          System.out.println("executing request " + httpPost.getURI());
          JSONObject jsonObject=null;
          // 执行post请求.    
          try {
        	 String outStr=JSONObject.toJSONString(templateMessage); 
        	  httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			 // 获取响应实体    
            HttpEntity entity = response.getEntity();  
            System.out.println(response.getStatusLine());    // 打印响应状态    
            if (entity != null) {  
                // 打印响应内容长度    
                System.out.println("回文长度: " + entity.getContentLength());  
                // 打印响应内容    
                String result= EntityUtils.toString(entity,"UTF-8");
              
                jsonObject= JSONObject.parseObject(result); 
            }   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          System.out.println("***********"+jsonObject);
		return jsonObject; 
		  
		  
	}
	/**
	 * 创建用户组
	 * @param token
	 * @return
	 */
	public static JSONObject createusergroup(String token){  
	 
		  String url=CREATE_USERGROUP_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject  =doPostStr(url, "{\"group\":{\"name\":\"test\"}}");
		  
		return jsonObject;  
		  
	}
	/**
	 * 查询用户组
	 * @param token
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject queryusergroup(String token) throws ClientProtocolException, IOException{  
	 
		  String url=QUERY_USERGROUP_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject  =doGetStr(url);		  
		return jsonObject;  
		  
	}
 
	
	
	
}
