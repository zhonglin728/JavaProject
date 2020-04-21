package com.dingding.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * jira系统的查询实体
 *
 * @author zhonglin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OaUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 英文名字
     */
    private String name;
    /**
     * 中文名字
     */
    private String nameEn;
}
