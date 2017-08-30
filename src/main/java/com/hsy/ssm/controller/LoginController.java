package com.hsy.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsy.ssm.pojo.LoginUser;


@Controller
public class LoginController {
	
	
	@RequestMapping("/loginPage.do")
	public String loginPage(HttpServletRequest request,HttpServletResponse response ) {
		 return "login";
	}
	
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,String username,String password ) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		
		request.getSession().setAttribute("loginUser", loginUser);
		
		return  "redirect:/main.do";
	}

}
