package com.hsy.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hsy.ssm.databaseUtil.DatasourceNameSetter;

public class SetDatasourceNameIntercepter implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
			String dsName = request.getParameter("dsName");
			if(dsName==null||dsName.length()==0){
				dsName="ds1";
			}
			DatasourceNameSetter.setDatasourceName(dsName);
			return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
