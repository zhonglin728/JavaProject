package org.spring.springboot.util;

import com.google.common.collect.ImmutableList;
import org.spring.springboot.enums.ColorEnum;
import org.spring.springboot.entity.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName GuavaTest
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/13
 * @Version V1.0
 **/
public class GuavaTest {

    private  static   List<Student> list1 = new ArrayList<Student>();
    private  static   List<Student> list2 = new ArrayList<Student>();

    static {
        list1.add(new Student("e",10,"24", ColorEnum.GREEN));
        list1.add(new Student("b",1,"23", ColorEnum.GREEN));
        list1.add(new Student("d",23,"20", ColorEnum.GREEN));
        list1.add(new Student("c",56,"45", ColorEnum.GREEN));
        list1.add(new Student("f",34,"78", ColorEnum.GREEN));
        list1.add(new Student("g",32,"89", ColorEnum.GREEN));
        list1.add(new Student("a",12,"47", ColorEnum.GREEN));

        list2.add(new Student("周杰伦",3,"25", ColorEnum.YELLO));
        list2.add(new Student("罗志祥",7,"13", ColorEnum.RED));

    }


    public static void main(String arg []) {
        //排序
        List<Student> collect2 = list1.stream().sorted((v1, v2) -> v1.getSex() - v2.getSex()).collect(Collectors.toList());
        List<Student> collect3 = list1.stream().sorted((v1, v2) -> v1.getAge().compareTo(v2.getAge())).collect(Collectors.toList());
        List<Student> collect4 = list1.stream().sorted((v1, v2) -> v2.getName().compareTo(v1.getName())).collect(Collectors.toList());
       // ImmutableTableList
        ImmutableList<List<Student>> of = ImmutableList.of(list1, list2);
        //Set<Student> collect = of.stream().flatMap(v-> v.stream()).collect(Collectors.toSet());
        ImmutableList<Object> build = ImmutableList.builder().add(list1).add(list2).build();






    }
}
