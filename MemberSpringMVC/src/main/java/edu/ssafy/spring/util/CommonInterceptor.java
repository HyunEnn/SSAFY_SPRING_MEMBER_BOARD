package edu.ssafy.spring.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getPathInfo();
		request.getSession().getAttribute("login");
		// TODO Auto-generated method stub
		/*
		 * return : true 이면 DS -> Controller로 넘어간다
		 * 			false 이면 DS -> Controller로 안넘기고 JSP 호출
		 *  
		 * 
		 */
		System.out.println("CommonInterceptor preHandler");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommonInterceptor PostHandler");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommonInterceptor AfterHandler");
	}

}
