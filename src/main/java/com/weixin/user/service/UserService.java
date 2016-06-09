package com.weixin.user.service;

import com.weixin.base.service.BaseService;
import com.weixin.user.model.User;

public interface UserService  extends BaseService<User>{

	String queryOpenidByCode(String code);

}
