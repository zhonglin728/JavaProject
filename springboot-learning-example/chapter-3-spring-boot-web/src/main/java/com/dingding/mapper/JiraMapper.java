package com.dingding.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.domain.OaUser;
import com.dingding.domain.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
@DS("jira")
public interface JiraMapper extends BaseMapper<User> {

    List<OaUser> getTaskOauth3();

    List<OaUser> getTaskOauth4();

    List<OaUser> getTaskOauth5();

    List<OaUser> getTaskOauth7();
}
