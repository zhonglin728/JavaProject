package org.spring.springboot.entity;

/**
 * @ClassName User
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/12
 * @Version V1.0
 **/

import lombok.*;
import org.spring.springboot.annotion.EncryptField;

/**
 * 如果没有添加@Setter注解，则无法使用setAge()等方法。
 * 使用lombok之后，省去了许多没必要的get，set，toString，equals，hashCode代码，
 简化了代码。
 * 注:@Data注解的作用相当于 @Getter @Setter @RequiredArgsConstructor
 @ToString @EqualsAndHashCode的合集。
  * 注:@Log 省去了在LombokTest中添加 getLogger的如下代码: * private static final java.util.logging.Logger log =
 java.util.logging.Logger.getLogger(LogExample.class.getName()); */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private int id;
    @Builder.Default
    @EncryptField
    private String name = "默认";
    private int age;
    @EncryptField
    private String address;
}
