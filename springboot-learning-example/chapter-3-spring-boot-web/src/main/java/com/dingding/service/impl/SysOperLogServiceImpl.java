package com.dingding.service.impl;

import com.dingding.domain.SysOperLog;
import com.dingding.mapper.SysOperLogMapper;
import com.dingding.service.ISysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-04-14
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {


    @Autowired
    private SysOperLogMapper sysOperLogMapper;
    @Override
    public List<?> getAll() {
        return sysOperLogMapper.selectList(null);
    }
}
