package org.spring.springboot.util;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/23 18:11
 * @Description:
 */
public class sortTest {


    public static void main(String[] args) throws Exception {
        Integer [] a = {34,56,1,2,78,24,999,444,332,0,234,57,78,40,29};
        for (int i=0;i<a.length-1;i++) {
            for(int j=0;j<a.length-1-i;j++){
                int temp = 0 ;
                if (a[j]>a[j+1]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }

        Arrays.stream(a).forEach(v->{
            System.out.println(v);
        });

        Arrays.stream(a).sorted().map(Integer::valueOf).forEach(v->{
            System.out.println("***"+v);
        });
        List<Integer> integers = Arrays.asList(a);

         Collections.reverse(Arrays.asList(a));

        String[] array = {"a","b","c"};
        List<String> strings = Arrays.asList(array);

        int i = friSum(4);
        System.out.println(i);
    }


    public static int friSum(int n) {
       return  n<=1?1:n * friSum(n - 1);
    }

}
