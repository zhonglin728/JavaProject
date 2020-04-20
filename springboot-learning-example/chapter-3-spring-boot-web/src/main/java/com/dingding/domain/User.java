package com.dingding.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉用户
 * @author zhonglin
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "ding_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工在当前企业内的唯一标识，也称staffId。可由企业在创建时指定，并代表一定含义比如工号，创建后不可修改
     */
    @TableField(value = "user_id")
    private String userid;

    /**
     * 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
     */
    @TableField(value = "union_id")
    private String unionid;

    /**
     * 表示人员在此部门中的排序，列表是按order的倒序排列输出的，即从大到小排列输出的

（钉钉管理后台里面调整了顺序的话order才有值）
     */
    @TableField(exist = false)
    private Integer order;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 分机号
     */
    private String tel;

    /**
     * 	
办公地点
     */
    private String workPlace;

    /**
     * 是否是企业的管理员，true表示是，false表示不是
     */
    private Boolean isAdmin;

    /**
     * 是否为企业的老板，true表示是，false表示不是
     */
    private Boolean isBoss;

    /**
     * 是否隐藏号码，true表示是，false表示不是
     */
    private Boolean isHide;

    /**
     * 是否是部门的主管，true表示是，false表示不是
     */
    private Boolean isLeader;

    /**
     * 姓名
     */
    private String name;

    /**
     * 英文Name
     */
    private String nameEn;

    /**
     * 成员所属部门id列表
     */
    private String department;

    /**
     * 职位信息
     */
    private String position;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 员工工号
     */
    private String jobnumber;

    /**
     * 创建时间！
     */
    private Date createTime;


}
