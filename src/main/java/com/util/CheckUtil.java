package com.util;

import java.util.Arrays;

public class CheckUtil {
	private static final String token="WXTEST";// 
	 /**
	  * 验证加密签名
	  * @param signature：加密签名
	  * @param timestamp：时间戳
	  * @param nonce:随机数
	  * @return
	  */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		// TODO Auto-generated method stub
		String[] arr=new String[]{token,  timestamp,  nonce};
		Arrays.sort(arr);//对数组进行字典排序
		/**
		 * 生成字符串
		 */
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		String temp=DecriptTest.SHA1(content.toString());//sha1加密
		return temp.equals(signature);
	}

}
