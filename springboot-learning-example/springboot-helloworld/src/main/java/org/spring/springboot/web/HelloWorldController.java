package org.spring.springboot.web;

import org.spring.springboot.interfunc.EmployeeInter;
import org.spring.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld 案例
 *
 *
 * @author bysocket
 * @date 16/4/26
 */
@RestController
public class HelloWorldController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/")
    public String sayHello() {

        studentService.truck();
        EmployeeInter.staticMethod();
        return "Hello,World!";

    }
}
