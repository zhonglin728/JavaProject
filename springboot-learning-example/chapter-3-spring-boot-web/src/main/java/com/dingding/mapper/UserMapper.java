package com.dingding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.domain.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
public interface UserMapper extends BaseMapper<User> {

    List<Map<String,?>> getTaskOauth3();

    List<Map<String,?>> getTaskOauth4();

    List<Map<String,?>> getTaskOauth5();

    List<Map<String,?>> getTaskOauth7();
}
