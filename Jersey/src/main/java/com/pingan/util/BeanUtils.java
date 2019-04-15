package com.pingan.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhonglin
 * @Date: 2019/4/15 13:05
 * @Description:
 */
public class BeanUtils {

    /**
     * 对象转Map
     * @param t
     * @param <T>
     * @return
     */
    public static  <T> Map convertBeanToMap (T t){
        Map map = new HashMap();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            String s = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                Method declaredMethod = t.getClass().getDeclaredMethod("get" + s,new Class[] {});
                try {
                    Object invoke = declaredMethod.invoke(t, new Class[] {});
                    if (field.getType().equals(String.class)){
                        map.put(field.getName(),String.valueOf(invoke));
                    }else if (field.getType().equals(int.class)){
                        map.put(field.getName(),Integer.parseInt(String.valueOf(invoke)));
                    }else if (field.getType().equals(double.class)){
                        map.put(field.getName(),Double.parseDouble(String.valueOf(invoke)));
                    }else if (field.getType().equals(Date.class)){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String format = simpleDateFormat.format(invoke);
                        map.put(field.getName(),String.valueOf(format));
                    }else{
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
