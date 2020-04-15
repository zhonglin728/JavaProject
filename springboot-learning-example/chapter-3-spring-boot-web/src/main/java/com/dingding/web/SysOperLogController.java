package com.dingding.web;


import com.dingding.service.ISysOperLogService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @Autowired
    private Configuration configuration;

    @GetMapping(value = "getAll")
    public List getAll(){
        List<?> list = iSysOperLogService.getAll();
        return list;
    }
    @GetMapping(value = "getFtl")
    public String getFtl(){
        configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
        try {
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("1报工全通知.ftl"), null);
            return html;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }


}
