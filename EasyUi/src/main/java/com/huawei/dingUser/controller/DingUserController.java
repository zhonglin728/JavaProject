package com.huawei.dingUser.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.dingUser.entity.DingUser;
import com.huawei.dingUser.mapper.DingUserMapper;
import com.huawei.dingUser.service.IDingUserService;
import com.huawei.dingUser.service.impl.DingUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-06-24
 */
@RestController
@RequestMapping("/user")
public class DingUserController  {
    @Autowired
    private DingUserMapper mapper;

    @GetMapping("/getUser")
    public IPage<?> getUser(){
        IPage<DingUser> page = new Page<DingUser>(1,10);
        LambdaQueryWrapper<DingUser> wa = new QueryWrapper<DingUser>().lambda().isNotNull(DingUser::getName).select();
        IPage<DingUser> dingUserIPage = mapper.selectPage(page, wa);
        return dingUserIPage;
    }
}
