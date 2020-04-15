package com.dingding.service;

import com.dingding.WebApplication;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.List;

/**
 * SpringBoot 测试类
 *
 * @RunWith:启动器 SpringJUnit4ClassRunner.class：让 junit 与 spring 环境进行整合
 * @SpringBootTest(classes={App.class}) 1, 当前类为 springBoot 的测试类
 * @SpringBootTest(classes={App.class}) 2, 加载 SpringBoot 启动类。启动springBoot
 * junit 与 spring 整合@Contextconfiguartion("classpath:applicationContext.xml")
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class DingServiceTest {
    //申请的小程序ID
    @Value("${ding.agentId}")
    private  Long agentId;
    @Value("${ding.appKey}")
    private  String appKey;
    @Value(value = "${ding.appSecre}")
    private  String appSecre;
    private  String zlUserId = "11392835271205976";
    @Autowired
    private DingService dingService;
    @Autowired
    private Configuration configuration;


    /**
     * 之前获取token
     */
    @Before
    public void getTokenScheduled(){
        dingService.gettoken();
    }

    /**
     * 全员发送消息！
     */
    @Test
    public void send_to_conversationTextScheduled(){
        try {
            configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("1报工全通知.ftl"), null);
            dingService.sendToConversationTextAll(agentId, content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取部门列表
     */
    @Test
    public void getDeptList(){
        List<?> deptList = dingService.getDeptList();
    }

}
