package com.dingding.service;

import com.dingding.domain.MsgTypeEnum;
import com.dingding.domain.TokenResultVo;
import com.dingding.util.DateUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

@Slf4j
@Component
public class DingService {

    //申请的小程序ID
    @Value("${ding.agentId}")
    private  Long agentId;
    @Value("${ding.appKey}")
    private  String appKey;
    @Value(value = "${ding.appSecre}")
    private  String appSecre;
    //获取Token接口
    @Value(value = "${ding.gettokenUrl}")
    private String gettokenUrl;
    // 发送消息接口
    @Value(value = "${ding.asyncsendUrl}")
    private String asyncsendUrl;
    //获取部门用户接口
    @Value(value = "${ding.deptUserUrl}")
    private String deptUserUrl;
    // 获取部门
    @Value(value = "${ding.deptUrl}")
    private String deptUrl;

    //token
    private String globToken = "";


    private  String zlUserId = "11392835271205976";
    private  String bylUserId = "manager7767";

    private  String zlPhone = "17688538142";

    private  String zlRoleId = "1183616592";


    @PostConstruct
    private void init() {
        getToken();
        log.info("首次加载token!");
    }
    public void runTest (){
        sendToConversationText(agentId,zlUserId, "现在时间："+DateUtils.getStringToday()+"请及时打卡！");
        /*send_to_conversationMarkdown(agentId,bylUserId,"公司通知！","# 今天吃什么"+ DateUtils.getStringToday()+"\n" +
                "菜单\n" +
                "1. 牛肉!\n" +
                "2. 猪肉!");*/

    }
    /**
     *  全部发送消息
     * @param agentId
     * @param content
     */
    public void sendToConversationTextAll(Long agentId,String content) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(asyncsendUrl);
            OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
            req.setAgentId(agentId);
            req.setToAllUser(true);
            OapiMessageCorpconversationAsyncsendV2Request.Msg obj1 = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            obj1.setMsgtype(MsgTypeEnum.TEXT.getType());
            OapiMessageCorpconversationAsyncsendV2Request.Text obj2 = new OapiMessageCorpconversationAsyncsendV2Request.Text();
            obj2.setContent(content);
            obj1.setText(obj2);
            req.setMsg(obj1);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req, globToken);
            printlnJson(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 指定人发送text消息
     * @param agentId 应用ID
     *  @param userId 用户ID
     * @param content 内容
     */
    public  void sendToConversationText(Long agentId,String userId,String content) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(asyncsendUrl);
            OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
            req.setAgentId(agentId);
            req.setUseridList(userId);
            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype(MsgTypeEnum.TEXT.getType());
            OapiMessageCorpconversationAsyncsendV2Request.Text text = new OapiMessageCorpconversationAsyncsendV2Request.Text();
            text.setContent(content);
            msg.setText(text);
            req.setMsg(msg);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req,globToken);
            //SendToConversationResultVo sendToConversationResultVo = objectMapper.readValue(rsp.getBody(), SendToConversationResultVo.class);
            printlnJson(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送markdown消息
     * @param agentId 应用ID
     * @param zlUserId 用户ID
     * @param title markdown标题
     * @param content markdown内容
     */
    public  void sendToConversationMarkdown(Long agentId,String zlUserId,String title ,String content) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(asyncsendUrl);
            OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
            req.setAgentId(agentId);
            req.setUseridList(zlUserId);
            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype(MsgTypeEnum.MARKDOWN.getType());
            OapiMessageCorpconversationAsyncsendV2Request.Markdown markdown = new OapiMessageCorpconversationAsyncsendV2Request.Markdown();
            markdown.setTitle(title);
            markdown.setText(content);
            msg.setMarkdown(markdown);
            req.setMsg(msg);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req, globToken);
            printlnJson(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 部门发送消息！
     */
    public void sendToConversationTextDept(){

    }
    /**
     * 获取所有的部门
     * @return
     */
    public List<OapiDepartmentListResponse.Department> getDeptList(){
        try {
            DingTalkClient client = new DefaultDingTalkClient(deptUrl);
            OapiDepartmentListRequest req = new OapiDepartmentListRequest();
            req.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentListResponse rsp = client.execute(req, globToken);
            List<OapiDepartmentListResponse.Department> list = rsp.getDepartment();
            return list;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }


    /**
     * 根据部门ID获取用户列表
     * simplelist
     * @param id 部门ID
     */
    public  List<OapiUserSimplelistResponse.Userlist> simplelist(Long id) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(deptUserUrl);
            OapiUserSimplelistRequest req = new OapiUserSimplelistRequest();
            req.setDepartmentId(id);
            req.setHttpMethod(HttpMethod.GET.name());
            OapiUserSimplelistResponse rsp = client.execute(req, globToken);
            List<OapiUserSimplelistResponse.Userlist> userlist = rsp.getUserlist();
            //printlnJson(rsp.getBody());
            return userlist;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }
    /**
     * 根据手机号码查询用户的userId
     */
    public  String getByMobile() {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_by_mobile");
            OapiUserGetByMobileRequest req = new OapiUserGetByMobileRequest();
            req.setMobile(zlPhone);
            req.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetByMobileResponse rsp = client.execute(req, globToken);
            String userid = rsp.getUserid();
            printlnJson(rsp.getBody());
            return userid;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取token
     */
    public  String getToken() {
        try {
            DingTalkClient client = new DefaultDingTalkClient(gettokenUrl);
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey(appKey);
            req.setAppsecret(appSecre);
            req.setHttpMethod(HttpMethod.GET.name());
            OapiGettokenResponse rsp = client.execute(req);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            TokenResultVo tokenResultVo = objectMapper.readValue(rsp.getBody(), TokenResultVo.class);
            String accessToken = tokenResultVo.getAccessToken();
            printlnJson(rsp.getBody());
            globToken = accessToken;
            return accessToken;
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }






    /**
     * 格式化JSON数据！
     * @param object
     */
    public void printlnJson(Object object){
        String s = String.valueOf(object);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object o = objectMapper.readValue(s, Object.class);
            log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
