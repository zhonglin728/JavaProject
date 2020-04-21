package org.spring.springboot.entity;

import lombok.*;
import org.spring.springboot.enums.ColorEnum;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private Integer sex;
    private String age;
    private ColorEnum colorEnum;

    public Student(String age, Integer sex) {
        this.sex = sex;
        this.age = age;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
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
