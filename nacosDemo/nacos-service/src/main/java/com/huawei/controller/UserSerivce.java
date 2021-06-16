package com.huawei.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2021/6/1416:02
 */
@Service
public class UserSerivce {
    public Map my(){
        return ImmutableMap.of("a",1);
    }
}

