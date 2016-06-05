package com.util;

import java.util.Arrays;

public class CheckUtil {
	private static final String token="WXTEST";// 
	 /**
	  * ��֤����ǩ��
	  * @param signature������ǩ��
	  * @param timestamp��ʱ���
	  * @param nonce:�����
	  * @return
	  */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		// TODO Auto-generated method stub
		String[] arr=new String[]{token,  timestamp,  nonce};
		Arrays.sort(arr);//����������ֵ�����
		/**
		 * �����ַ���
		 */
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		String temp=DecriptTest.SHA1(content.toString());//sha1����
		return temp.equals(signature);
	}

}
