package com.pingan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pingan.entity.User;



@Service
public class UserService {
	
	private List<User> userList;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	
	
	public String getUser(){
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setName("钟林"+i);
			user.setAddress("湖北襄阳"+i);
			list.add(user);
		}
		Gson gson = new Gson();
		return gson.toJson(list).toString();
	}
	
	
	
}
