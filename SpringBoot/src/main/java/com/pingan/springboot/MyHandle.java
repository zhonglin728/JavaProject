package com.pingan.springboot;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandle implements HandlerInterceptor {
	/**
	 * 前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		int i = 1;
		try {
			Map<String, String[]> parmMap = request.getParameterMap();
			Iterator<String> iter = parmMap.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				Object value = parmMap.get(key)[0];
				System.out.println("第" + i + "个param---->"+ key +"=="+ value);
				i = i + 1;
			}
		} catch (Exception e) {
			i = 1;
		}
		i = 1;
		return true;
	}
	/**
	 * 完成
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post");
		System.out.println(modelAndView);
	}
	/**
	 * 后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after");
		System.out.println(handler);
	}

}
