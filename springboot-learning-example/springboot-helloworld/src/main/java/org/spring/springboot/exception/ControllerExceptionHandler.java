package org.spring.springboot.exception;

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
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map runtimeExceptionHandler(RuntimeException ex) {
        Map map = new HashMap();
        map.put("500","拦截到异常信息@！");
        return map;
    }
}
