package org.spring.springboot.web;

import com.google.common.collect.Lists;
import org.spring.springboot.annotion.Encryption;
import org.spring.springboot.entity.User;
import org.spring.springboot.entity.Users;
import org.spring.springboot.response.ResponseResult;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/16 16:43
 * @Description:
 */
@RequestMapping(value = "user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequestMapping(value = "getUser")
    public ResponseEntity<ResponseResult> getUser() {
        List<Users> user = userService.getUser();
        ResponseResult res = new ResponseResult<>(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping
    @RequestMapping(value = "save")
    public ResponseEntity<ResponseResult> saveUser(@Encryption @RequestBody User user){
        ResponseResult res = new ResponseResult<>(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
