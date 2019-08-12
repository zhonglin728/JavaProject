package org.spring.springboot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Hua-cloud
 * @Auther: zhonglin
 * @Date: 2019/7/4 16:03
 * @Description:
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Student {

    private String name;
    private  Integer sex;
    private  String age;
    private Color color;

    public Student() {
    }

    public Student(String name, Integer sex, String age,Color color) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.color = color;
    }


    public Student(String age, Integer sex) {
        this.sex = sex;
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
