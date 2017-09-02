package com.hsy.ssm.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsy.ssm.service.VideoCategoryService;


/** 
 * 登录认证的控制器 
 */  
@Controller  
public class IndexControl {  
	
	private static final Logger logger = LoggerFactory.getLogger(IndexControl.class);
	
	@Resource
	private VideoCategoryService videoCategoryService;
	
	@RequestMapping("/main.do")
	public String home( ){
		logger.debug("xxxx");
		logger.info("xxxx");
		logger.warn("xxxx");
		logger.error("xxxx");
		
		return "main";
	}
	
	
	@RequestMapping("/testTransaction.do")
	public void testTransaction(int a,int b ){
		System.out.println(a);
		System.out.println(b);
		videoCategoryService.addVideoCategory_testTransaction(a,b);
		
		
	}
	/*
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
    }  */
}  