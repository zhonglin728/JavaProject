package org.spring.springboot.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/2 20:58
 * @Description:
 */
public class ImmuTest {

    public static void main(String arg []) {
        //字符串装到List里面
        ArrayList<String> strings1 = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g");
        //字符串装到List里面
        List<String> collect = Stream.of("a", "b", "c", "d", "e", "f", "g").collect(Collectors.toList());
        //字符串装到List里面
        List<String> of = ImmutableList.of("a", "b", "c", "d", "e", "f", "g");

        // List 转 String 分割 1
        String join = Joiner.on(",").skipNulls().join(of);
        // List 转 String 分割 2
        String collect2 = of.stream().collect(Collectors.joining(","));
        //String 转 List 1
        List<String> strings = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(join);
        //String 转 List 2
        List<String> collect1 = Arrays.stream(join.split(",")).collect(Collectors.toList());

        //字符串 拆分 再 拆分
        String str = "xiaoming=11,xiaohong=23";
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);

        //字符串多条件拆分装入List
        String input = "aa.dd,,ff,,.";
        List<String> result = Splitter.onPattern("[.|,]").omitEmptyStrings().trimResults().splitToList(input);

        //ImmutableMap装入数据
        ImmutableMap<String, String> of1 = ImmutableMap.of("a", "sddd", "b", "kjkkj");
        ImmutableMap<Object, Object> build3 = ImmutableMap.builder().put("a", 1).put("b", 2).build();



    }
}
