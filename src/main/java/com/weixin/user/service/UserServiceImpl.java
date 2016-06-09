package com.weixin.user.service;

import java.io.IOException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
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

 
}
