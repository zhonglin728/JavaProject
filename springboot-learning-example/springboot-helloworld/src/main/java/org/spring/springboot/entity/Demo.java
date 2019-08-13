package org.spring.springboot.entity;

import lombok.*;

/**
 * @Data 注解等于以下所有场景！
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Demo {

    private String name;
    private  Integer sex;
    private  String age;

    public Demo(String age,Integer sex) {
        this.age =age;
        this.sex =sex;
    }



}
