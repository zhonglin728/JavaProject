package org.spring.springboot.exception;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControllerExceptionHandler
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/14
 * @Version V1.0
 **/
@ControllerAdvice(basePackages = "org.spring.springboot.web",basePackageClasses = RestController.class)
public class ControllerExceptionHandler {

    //运行时异常
    @ExceptionHandler(OrderPeriodException.class)
    @ResponseBody
    public ResponseEntity<Map> runtimeExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<Map>(ImmutableMap.of("content",ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
