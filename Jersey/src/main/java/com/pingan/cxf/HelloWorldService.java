package com.pingan.cxf;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.pingan.entity.User;
/**
 * cxf  服务 实现  2017-6-13 16:05:28
 * @author Administrator
 * http://localhost:8080/Jersey/service/cxf?wsdl
 *
 */
@WebService(endpointInterface="com.pingan.cxf.IHelloWorldService")//endpointInterface是为了在实现多个接口情况下指明webservice的接口
public class HelloWorldService implements IHelloWorldService {

	@Override
	public List<User> query(String name) {
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("钟林"+i);
			user.setAddress("湖北十堰"+i);
			list.add(user);
		}
		return list;
	}

	@Override
	public void add(User user) {
		System.out.println(user.getName());
		
	}

	@Override
	public void delete(String id) {
		System.out.println(id);
		
	}

	@Override
	public Integer update(User user) {
		return 1;
	}

   
}
