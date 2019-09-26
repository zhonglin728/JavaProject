package org.spring.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 13:38
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Dict extends BaseEntity{
    private Integer dictCode;
    private Integer dictSort;
    private String dictLabel;
    private String dictType;
}
