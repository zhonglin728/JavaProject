package org.spring.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 12:40
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Users extends BaseEntity{
    private Integer userId;
    private Integer deptId;
    private String loginName;
    private String userName;
    private String userType;
    private String email;
    private String phoneNumber;
    //private String sex;
    private String avatar;
    private String password;
    private String status;
    private String delFlag;
    private String loginIp;
    private String loginDate;
    private String remark;
    private Dict dict;
    private List<Role> role;
}
