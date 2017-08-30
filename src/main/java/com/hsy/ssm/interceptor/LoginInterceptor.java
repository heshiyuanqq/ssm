package com.hsy.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hsy.ssm.pojo.LoginUser;

public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		System.out.println("request in interceptor="+request.getClass()+"    请求URL="+request.getRequestURL()+"    请求URI="+request.getRequestURI());
		LoginUser loginUser= (LoginUser) request.getSession().getAttribute("loginUser");
		
		if(loginUser!=null&&loginUser.getUsername()!=null){//已经登录
				return true;
		}else{//未登录
			    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				return false;
		}
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
