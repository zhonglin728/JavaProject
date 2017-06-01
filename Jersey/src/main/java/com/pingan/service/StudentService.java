package com.pingan.service;

import org.springframework.stereotype.Service;


@Service("zhonglin")
public class StudentService {
	
	public String getStudent(){
		return "我是钟林！";
	}

}
