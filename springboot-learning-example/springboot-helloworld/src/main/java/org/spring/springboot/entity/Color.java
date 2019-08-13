package org.spring.springboot.entity;

import java.util.Arrays;

/**
 * @author Hua-cloud
 */

public enum Color {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName2(int index) {
       String name = Arrays.stream(Color.values())
               .filter(v -> v.getIndex() == index)
               .findFirst()
               .map(Color::getName)
               .orElse("未找到！");
        return name;
    }
    public static int getIndex2(String name) {
         int index = Arrays.stream(Color.values())
                 .filter(v -> v.getName().equals(name))
                 .findFirst()
                 .map(Color::getIndex)
                 .orElse(0);
        return index;
    }
    // 普通方法 通过index获取Name
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return "";
    }
    // 普通方法 通过name获取index
    public static int getIndex(String name) {
        for (Color c : Color.values()) {
            if (c.getName() == name) {
                return c.index;
            }
        }
        return 0;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
