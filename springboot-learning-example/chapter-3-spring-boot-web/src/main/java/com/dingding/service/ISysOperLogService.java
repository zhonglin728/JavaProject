package com.dingding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingding.domain.SysOperLog;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-04-14
 */
public interface ISysOperLogService extends IService<SysOperLog> {

    public List<?> getAll();


}
