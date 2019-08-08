package org.spring.springboot.entity;

/**
 * @Auther: zhonglin
 * @Date: 2019/7/4 16:03
 * @Description:
 */
public class Student {

    private String name;
    private  Integer sex;
    private  String age;

    public Student() {
    }

    public Student(String name, Integer sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }


    public Student(String age, Integer sex) {
        this.sex = sex;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }
}
