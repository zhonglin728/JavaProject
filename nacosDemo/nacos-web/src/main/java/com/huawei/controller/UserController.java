package com.huawei.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2021/6/1415:09
 */
@RestController
public class UserController {

    @NacosValue(value = "${username:null}", autoRefreshed = true)
    private String username;

    @NacosInjected
    private NamingService namingService;
    @Autowired
    private UserSerivce userSerivce;

    @RequestMapping(value = "/getUser")
    public String getUser() {
        return username;
    }
    @RequestMapping(value = "/getUser1")
    public Map getUser1() {
        return userSerivce.my();
    }
}
