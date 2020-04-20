package com.dingding.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.domain.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
@DS("ding")
public interface DingMapper extends BaseMapper<User> {

    int insertOrUpdate(User user);

}
