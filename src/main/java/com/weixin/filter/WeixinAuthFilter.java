package com.weixin.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.WeixinFinalValue;
import com.common.WeixinUtil;
import com.weixin.user.model.User;
import com.weixin.user.service.UserService;

 

public class WeixinAuthFilter implements Filter {
	@Autowired
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User tu = (User)req.getSession().getAttribute("user");
 
		if(tu==null) {
			String agent = req.getHeader("User-Agent");
			
			System.out.println(agent);
			
			if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				System.out.println(code+"------------"+state);
				if(code!=null&&state!=null&&state.equals("1")) { 
			 
					String openid = userService.queryOpenidByCode(code);//通过Code获取openid来进行授权
					/*if(openid!=null) {
						IUserService userService = (IUserService)BeanFactoryContext.getService("userService");
						User u = userService.loadByOpenid(openid);
						if(u==null) {
							u = wUserService.queryByOpenid(openid).getUser();
							userService.add(u);
						} else {
							if(u.getStatus()==0) {
								u.setStatus(1);
								userService.update(u);
							}
						}
						hRequest.getSession().setAttribute("user", u);
					}*/
				} else {
					String path = req.getRequestURL().toString();
					String query = req.getQueryString();
					if(query!=null) {
						path = path+"?"+query;
					}
					
					System.out.println("************"+path);
					
					
					
					String uri = WeixinFinalValue.AUTH_URL;
					uri = uri.replace("APPID", WeixinUtil.APPID)
					  // .replace("REDIRECT_URI",URLEncoder.encode(path, "UTF-8"))
							 .replace("REDIRECT_URI",path)
					   .replace("SCOPE", "snsapi_base")
					   .replace("STATE", "1");
					System.out.println("uri:++"+uri);
					res.sendRedirect(uri);
					System.out.println(uri);
					//return;
				}
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}

}
