package com.dingding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.domain.User;
import com.dingding.mapper.DingMapper;
import com.dingding.service.IDingUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhonglin
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<DingMapper, User> implements IDingUserService {

}
