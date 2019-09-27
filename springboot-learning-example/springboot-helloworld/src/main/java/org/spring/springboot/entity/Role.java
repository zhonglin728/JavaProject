package org.spring.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 12:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Role extends BaseEntity{
    private Integer roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private String delFlag;
    private String remark;
    private List<Dept> dept;
}
