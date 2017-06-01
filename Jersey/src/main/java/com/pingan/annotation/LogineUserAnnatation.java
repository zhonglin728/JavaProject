package com.pingan.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.pingan.entity.User;
public class LogineUserAnnatation extends HandlerMethodArgumentResolverComposite {
	/**
	 * 自定义注解实现参数注解绑定！
	 * 2017-5-6 19:34:27
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	    if(
	            //如果该参数注解有@Logined
	            parameter.getParameterAnnotation(UserInfo.class)!=null&&
	            //如果该参数的类型为User
	            parameter.getParameterType()==User.class
	            ){
	        //支持解析该参数
	        return true;
	    }
	    return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
	        ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
	        WebDataBinderFactory binderFactory) throws Exception {
	        HttpServletRequest request= (HttpServletRequest) webRequest.getNativeRequest();
	    //这里暂时把User对象放在session中
	        User user= new User();
	        user.setName("钟林");
	        user.setAddress("湖北襄阳！");
	        return user;
	    }

}
