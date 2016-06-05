package com.common;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class WeixinUtilTest {
public static void main(String[] args){
	 AccessToken accessToken=WeixinUtil.getAccessToken();
	 
	 System.out.println("测试图片上传");
 
	String token="J6BogYXGjP4W1Yvjce0TQ4raBJoM8DSkC4owpF1sNNAK1l3E5MSJAp3NwBp3B2VEjEst1QhoaDzA424eSoGF3kX8FvLcWBkh65x_bPOgAVZeABAQLP";
	 uploadImage(accessToken.getAccess_token());
}
public  static String uploadImage(String accessToken){
	String filePath="d:/1.jpg";
	String	result=null;
	try {
		 	result=WeixinUtil.upload(filePath, accessToken, "thumb");
	} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("图片上传返回值"+result);
	return result;
	
}

}
