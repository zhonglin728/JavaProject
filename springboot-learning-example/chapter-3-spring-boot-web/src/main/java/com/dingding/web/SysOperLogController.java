package com.dingding.web;


import com.dingding.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/sysOperLog")
public class SysOperLogController {

    @Autowired
    private ISysOperLogService iSysOperLogService;

    @GetMapping(value = "getAll")
    public List getAll(){
        List<?> list = iSysOperLogService.getAll();
        return list;
    }

}
