package edu.ssafy.spring.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor2 implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getServletPath());
		//request.getSession().getAttribute("login");
		// TODO Auto-generated method stub
		/*
		 * return : true 이면 DS -> Controller 넘어감니다
		 * 			false 이면 DS -> Controller 안 넘기고 jsp 호출
		 */
		System.out.println("commonInterceptor2 PreHandler");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("commonInterceptor2 PostHandler");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("commonInterceptor2 afterCompletion");
	}
	
}
