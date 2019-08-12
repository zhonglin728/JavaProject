package org.spring.springboot.service;

import org.spring.springboot.abstracts.EmployeeAbs;
import org.spring.springboot.interfunc.EmployeeInter;
import org.springframework.stereotype.Service;

/**
 * @ClassName StudentService
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/12
 * @Version V1.0
 **/
@Service
public class StudentService implements EmployeeInter {


    @Override
    public void print() {
        System.out.println(COMMON);

    }

    @Override
    public void train() {

    }

    @Override
    public void air() {

    }
}
