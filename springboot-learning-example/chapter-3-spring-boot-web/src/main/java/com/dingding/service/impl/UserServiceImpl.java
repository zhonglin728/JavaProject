package com.dingding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.domain.User;
import com.dingding.mapper.UserMapper;
import com.dingding.service.IDingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IDingUserService {

    @Autowired
    private UserMapper userMapper;

    public Integer selectCount(LambdaQueryWrapper<User> lambdaQueryWrapper){
        return userMapper.selectCount(lambdaQueryWrapper);
    }

    public Integer insert(User user){
        return userMapper.insert(user);
    }

}
