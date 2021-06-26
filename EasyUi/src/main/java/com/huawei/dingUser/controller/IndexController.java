package com.huawei.dingUser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.GenericArrayType;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2021/6/2612:00
 */
@Controller
public class IndexController {
    @GetMapping("index")
    public String index(){
        return "index";
    }
}
