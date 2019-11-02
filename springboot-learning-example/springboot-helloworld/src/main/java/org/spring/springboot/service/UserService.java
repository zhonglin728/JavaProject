package org.spring.springboot.service;

import org.spring.springboot.dao.UserMapper;
import org.spring.springboot.entity.User;
import org.spring.springboot.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 12:3734343
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<Users> getUser(){
        return userMapper.getUser();
    }
    public Users findByUserName(Map map){
        return userMapper.findByUserName();
    }

}
