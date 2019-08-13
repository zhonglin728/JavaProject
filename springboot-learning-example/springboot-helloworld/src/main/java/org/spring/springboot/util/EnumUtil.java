package org.spring.springboot.util;

import org.spring.springboot.enums.ColorEnum;

/**
 * @Auther: zhonglin
 * @Date: 2019/8/13 20:14
 * @Description:
 */
public class EnumUtil {

    public static  void main(String args []){
        // 枚举的使用！
        System.out.println( ColorEnum.getName2(2));
        System.out.println(ColorEnum.getIndex2("绿色"));
    }
}
