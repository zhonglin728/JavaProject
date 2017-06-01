package com.pingan.springboot;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/user
 * @author ZhongLin728
 *
 */
@RestController
public class UserBoot {
	@RequestMapping(value="/user")
	public Map<?, ?> view(HttpServletRequest request){
		   Map<String, Object> headMap = new HashMap<String, Object>();
		   Enumeration<String> headerNames = request.getHeaderNames();//head
		   while (headerNames.hasMoreElements()) {
			String name = (String) headerNames.nextElement();
			String value = request.getHeader(name);
				headMap.put(name, value);
		   }
		    Map<String, Object> parMap = new HashMap<String, Object>();
		    Map<String, String[]> parameterMap = request.getParameterMap();//parameter
			Iterator<Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<java.lang.String, java.lang.String[]> entry = (Map.Entry<java.lang.String, java.lang.String[]>) iterator.next();
				parMap.put(entry.getKey(), entry.getValue()[0]);
			}
			headMap.putAll(parMap);
		return headMap;
	
	}	
	
	
	
}
