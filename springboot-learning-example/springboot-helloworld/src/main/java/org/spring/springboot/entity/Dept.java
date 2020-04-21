package org.spring.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhonglin
 * @Date: 2019/9/26 16:23
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Dept extends BaseEntity {
    private Integer deptId; //部门id
    private Integer parentId; //0' COMMENT '父部门id
    private String ancestors; //' COMMENT '祖级列表
    private String deptName; //' COMMENT '部门名称
    private Integer orderNum; //0' COMMENT '显示顺序
    private String leader; //' COMMENT '负责人
    private String phone; //' COMMENT '联系电话
    private String email; //' COMMENT '邮箱
    private String status; //0' COMMENT '部门状态（0正常 1停用）
    private String delFlag; //0' COMMENT '删除标志（0代表存在 2代表删除）


}
