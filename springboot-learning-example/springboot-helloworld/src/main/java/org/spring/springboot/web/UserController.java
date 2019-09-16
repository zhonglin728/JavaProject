package org.spring.springboot.web;

import org.spring.springboot.annotion.Encryption;
import org.spring.springboot.entity.User;
import org.spring.springboot.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/16 16:43
 * @Description:
 */
@RequestMapping(value = "user")
@RestController
public class UserController {

    @PostMapping
    @RequestMapping(value = "save")
    public ResponseEntity<ResponseResult> saveUser(@Encryption @RequestBody User user){
        ResponseResult res = new ResponseResult<>(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
