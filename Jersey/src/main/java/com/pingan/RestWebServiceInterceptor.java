package com.pingan;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
/**
 * Jersey  filter 所有的web.xml；里面配置的访问路径  /rest/都会进这个方法！在这里可以做一些权限 登录验证！
 * @author Administrator
 *
 */
public class RestWebServiceInterceptor implements ContainerRequestFilter {
	@Context
    HttpServletRequest request;
    @Context
    private HttpServletResponse response;
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		String servletPath = request.getServletPath();
		System.out.println("-----------------------------Filter---------------"+servletPath);
	}

}
