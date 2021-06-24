package com.huawei.dingUser.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.dingUser.entity.DingUser;
import com.huawei.dingUser.service.IDingUserService;
import com.huawei.dingUser.service.impl.DingUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IDingUserService dingUserService;

    @PostMapping("/getUser")
    public DingUser getUser(){
        IPage<DingUser> dingUserIPage = new IPage<DingUser>();
        return dingUserService.
    }
}
