package com.hsy.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsy.ssm.pojo.User;

/** 
 * 登录认证的控制器 
 */  
@Controller  
public class IndexControl {  
	
	@RequestMapping("/main.do")
	public String home( ){
		return "main";
	}
	
	@RequestMapping("/demo1.do")
	public String demo1( ){
		return "http://www.baidu.com";
	}
	@RequestMapping("/demo2.do")
	public void demo2(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		request.getRequestDispatcher("http://www.baidu.com").forward(request, response);
	}
	@RequestMapping("/demo3.do")
	public void demo3(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		 response.sendRedirect("http://www.baidu.com");
	}
	
	@RequestMapping("/loginPage")
	public String loginPage( ){
		return "login";
	}
  
    @RequestMapping(value="/login")  
    public String login(HttpSession session,String username,String password) throws Exception{        
        if(username==null||password==null){
        	 return "redirect:loginPage";   
        }
        User user = new User();
        user.setUserName(username);
        session.setAttribute("user", user);
        System.out.println("用户"+user.getUserName()+"登录！");
        //重定向  
        return "redirect:home";   
    }  
      
    @RequestMapping(value="/logout")  
    public String logout(HttpSession session) throws Exception{  
        //清除Session  
    	User existUser = (User) session.getAttribute("user");
    	if(existUser!=null){
    		System.out.println("用户"+existUser.getUserName()+"退出！");
    	}
    	
        session.invalidate();  
        return "redirect:loginPage";  
    }  
}  