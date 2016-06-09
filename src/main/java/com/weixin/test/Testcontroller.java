package com.weixin.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weixin.user.service.UserService;

@Controller
@RequestMapping(value = "test")
public class Testcontroller {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("测试");
		out.println(userService.queryCount());
		out.println("<HTML><HEAD><TITLE>输出HTML标签</HEAD></TITLE><BODY>");
		out.println("输出程序中你想输出的部分");
		out.println("</BODY></HTML>");
		out.close();
	}
}
