package com.pingan.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingan.entity.User;
import com.pingan.service.UserService;

@Component
@Path("/hellow")
public class UserRestful {
	/**
	 * ��ȡSpring Bean  user �������õ�ֵ��a
	 */
	@Autowired
	private User user1;
	
	/**
	 * ��ȡSpring Bean SpringUser �������õ�ֵ��
	 */
	@Autowired
	private UserService SpringUser;
	
	@Autowired
	private UserService userService;
	@Context
	private HttpServletRequest request;
	@Autowired
	private HttpServletRequest res;
	
	/**
	 * http://localhost:8080/Jersey/rest/hellow?name=zhonglin
	 * @return
	 * @throws InterruptedException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser() throws InterruptedException{
		String user = userService.getUser();
		List<User> list = SpringUser.getUserList();
		String param = request.getParameter("name");
		String result = user+list.get(0).getName()+list.get(0).getAddress()+"请求参数为"+param;
		return Response.ok(result).build();
	}
	
//	public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
//		User user = new User();
//		Method declaredMethod = User.class.getDeclaredMethod("setName", String.class);
//		declaredMethod.invoke(user, "rest");
//		System.out.println(user.getName());
//		Object invoke = User.class.getMethod("getName", new Class[]{}).invoke(user);
//		System.out.println(invoke);
//	}

}
