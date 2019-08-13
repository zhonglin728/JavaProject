package org.spring.springboot.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.UnmodifiableIterator;
import org.spring.springboot.entity.Color;
import org.spring.springboot.entity.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName GuavaTest
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/13
 * @Version V1.0
 **/
public class GuavaTest {

    private  static   List<Student> list = new ArrayList<Student>();
    static {
        list.add(new Student("e",10,"24", Color.GREEN));
        list.add(new Student("b",1,"23",Color.GREEN));
        list.add(new Student("d",23,"20",Color.GREEN));
        list.add(new Student("c",56,"45",Color.GREEN));
        list.add(new Student("f",34,"78",Color.GREEN));
        list.add(new Student("g",32,"89",Color.GREEN));
        list.add(new Student("a",12,"47",Color.GREEN));
    }


    public static void main(String arg []) {
       // ImmutableTableList
        List<String> list1 = Arrays.asList("1", "34", "23", "55");
        List<String> list2 = Arrays.asList("36", "45", "89", "97");
        ImmutableList<List<String>> of = ImmutableList.of(list1, list2);
        Set<String> collect = of.stream().flatMap(v-> v.stream()).collect(Collectors.toSet());
        Set<String> collect1 = collect.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toSet());


        List<Student> collect2 = list.stream().sorted((v1, v2) -> v1.getSex() - v2.getSex()).collect(Collectors.toList());
        List<Student> collect3 = list.stream().sorted((v1, v2) -> v1.getAge().compareTo(v2.getAge())).collect(Collectors.toList());
        List<Student> collect4 = list.stream().sorted((v1, v2) -> v2.getName().compareTo(v1.getName())).collect(Collectors.toList());

        Integer a = 5;
        System.out.println( a.compareTo(4));
        String str = "b";
        System.out.println(str.compareTo("r"));


    }
}
