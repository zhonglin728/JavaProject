package org.spring.springboot.annotion;

import java.lang.annotation.*;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/16 19:53
 * @Description:
 */
@Deprecated
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface EncryptField {

}
