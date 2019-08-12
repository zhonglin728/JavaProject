package org.spring.springboot.interfunc;

import org.spring.springboot.entity.Student;

/**
 * @ClassName MyFunction
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/12
 * @Version V1.0
 **/
@FunctionalInterface
public interface MyFunction {
    /**
     * 自定义函数  function
     * @param id
     * @return
     */
    public Student findStudent(String id);

    //public void findStudentById(String name);
}
