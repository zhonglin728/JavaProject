package com.dingding.web;


import com.dingding.domain.OaUser;
import com.dingding.domain.User;
import com.dingding.mapper.DingMapper;
import com.dingding.mapper.JiraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private DingMapper dingMapper;

    @Autowired
    private JiraMapper jiraMapper;

    @GetMapping(value = "getDingUser")
    public ResponseEntity<?> getDingUser(){
        List<User> users = dingMapper.selectList(null);
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping(value = "getJiraUser")
    public ResponseEntity<?> getJiraUser(){
        List<OaUser> oaUsers = jiraMapper.getTaskOauth3();
        return new ResponseEntity(oaUsers, HttpStatus.OK);
    }

}
