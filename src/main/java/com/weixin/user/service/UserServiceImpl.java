package com.weixin.user.service;

import java.io.IOException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.common.WeixinFinalValue;
import com.common.WeixinUtil;
import com.weixin.base.dao.BaseDao;
import com.weixin.base.service.BaseServiceImpl;
import com.weixin.user.dao.UserDao;
import com.weixin.user.mapper.UserMapper;
import com.weixin.user.model.User;

@Service
@Transactional
public class UserServiceImpl<Uer> extends BaseServiceImpl<User> implements UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private UserMapper mapper;

	@Override
	protected BaseDao<User> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public String queryOpenidByCode(String code)  {
		 
			String url = WeixinFinalValue.AUTH_GET_OID;//通过code换取网页授权access_token
			url = url.replace("APPID", WeixinUtil.APPID).replace("SECRET",WeixinUtil.APPSECRET) .replace("CODE", code);
			JSONObject jsonObject=null;
			try {
				jsonObject = WeixinUtil.doGetStr(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String openid = jsonObject.getString("openid");
			System.out.println(jsonObject+"------openid--------"+openid);
			return openid;
	 
	}
}
