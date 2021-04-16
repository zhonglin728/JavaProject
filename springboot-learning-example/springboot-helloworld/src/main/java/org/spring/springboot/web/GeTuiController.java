package org.spring.springboot.web;

import org.spring.springboot.getui.GeTuiService;
import org.spring.springboot.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/914:32
 */
@RequestMapping(value = "getui")
@RestController
public class GeTuiController {
    @Autowired
    private GeTuiService geTuiService;

    @RequestMapping("send")
    public ResponseEntity<ResponseResult> send() {
        geTuiService.sendSingle();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
