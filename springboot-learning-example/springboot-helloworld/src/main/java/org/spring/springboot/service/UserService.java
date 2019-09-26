package org.spring.springboot.service;

import org.spring.springboot.dao.UserMapper;
import org.spring.springboot.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 12:37
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<Users> getUser(){
        return userMapper.getUser();
    }

}
