package com.dingding.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingding.domain.MsgTypeEnum;
import com.dingding.domain.TokenResultVo;
import com.dingding.domain.User;
import com.dingding.service.impl.UserServiceImpl;
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
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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

    @Autowired
    private UserServiceImpl userService;
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
    //获取部门用户简略信息
    @Value(value = "${ding.deptUserUrl}")
    private String deptUserUrl;
    //获取部门下的用户详细信息
    @Value(value = "${ding.deptUserInfoUrl}")
    private String deptUserInfoUrl;
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
        syncUser();
        log.info("同步钉钉用户信息到本地库完毕！");
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
     * @return 任务ID
     */
    public Long sendToConversationTextAll(Long agentId,String content) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(asyncsendUrl);
            OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
            req.setAgentId(agentId);
            //全员发送  每天只能三次  钉钉接口限制！
            req.setToAllUser(true);
            OapiMessageCorpconversationAsyncsendV2Request.Msg obj1 = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            obj1.setMsgtype(MsgTypeEnum.TEXT.getType());
            OapiMessageCorpconversationAsyncsendV2Request.Text obj2 = new OapiMessageCorpconversationAsyncsendV2Request.Text();
            obj2.setContent(content);
            obj1.setText(obj2);
            req.setMsg(obj1);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req, globToken);
            printlnJson(rsp.getBody());
            return rsp.getTaskId();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return 0L;
    }
    /**
     *
     * 指定人发送text消息
     * @param agentId 应用ID
     *  @param userId 用户ID
     * @param content 内容
     * @return 任务ID
     */
    public Long sendToConversationText(Long agentId, String userId, String content) {
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
            printlnJson(rsp.getBody());
            return rsp.getTaskId();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 发送markdown消息
     * @param agentId 应用ID
     * @param zlUserId 用户ID
     * @param title markdown标题
     * @param content markdown内容
     * @return 任务ID
     */
    public  Long sendToConversationMarkdown(Long agentId,String zlUserId,String title ,String content) {
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
            return rsp.getTaskId();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * 部门发送消息！
     * @param deptList 部门ID
     * @param content 消息内容
     * @return 任务ID
     */
    public Long sendToConversationTextDept(String deptList,String content){
        try {
            DingTalkClient client = new DefaultDingTalkClient(asyncsendUrl);
            OapiMessageCorpconversationAsyncsendV2Request req = new OapiMessageCorpconversationAsyncsendV2Request();
            req.setAgentId(agentId);
            req.setDeptIdList(deptList);
            OapiMessageCorpconversationAsyncsendV2Request.Msg obj1 = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            obj1.setMsgtype(MsgTypeEnum.TEXT.getType());
            OapiMessageCorpconversationAsyncsendV2Request.Text obj2 = new OapiMessageCorpconversationAsyncsendV2Request.Text();
            obj2.setContent(content);
            obj1.setText(obj2);
            req.setMsg(obj1);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(req, globToken);
            printlnJson(rsp.getBody());
            log.info("部门发送消息！");
            return rsp.getTaskId();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return 0L;
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
     * 获取部门下的用户详细信息！
     * @param id 部门ID
     * @param offSet 分页
     * @param size 页码
     */
    public  List<OapiUserListbypageResponse.Userlist> getUserListInfo(Long id,Long offSet,Long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(deptUserInfoUrl);
            OapiUserListbypageRequest req = new OapiUserListbypageRequest();
            req.setDepartmentId(id);
            req.setOffset(offSet);
            req.setSize(size);
            req.setHttpMethod(HttpMethod.GET.name());
            OapiUserListbypageResponse rsp = client.execute(req, globToken);
            List<OapiUserListbypageResponse.Userlist> userlist = rsp.getUserlist();
            return userlist;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }


    /**
     * 根据部门ID获取用户列表简略信息
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
     * 同步用户信息！
     */
    public void syncUser(){
        //获取所有部门
        List<OapiDepartmentListResponse.Department> deptList = getDeptList();
        List<OapiUserListbypageResponse.Userlist> userlists = new ArrayList<>();
        deptList.forEach(v->{
            List<OapiUserListbypageResponse.Userlist> list = getUserListInfo(v.getId(), 1,Lists.newArrayList());
            userlists.addAll(list);
        });
        //根据Useid去重
        ArrayList<OapiUserListbypageResponse.Userlist> collect = userlists.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(OapiUserListbypageResponse.Userlist::getUserid))), ArrayList::new)
        );
        //数据重组  将钉钉的对象转为自己书写的对象！
        List<User> userList = collect.stream().map(v -> {
            User user = new User().setCreateTime(new Date()).setNameEn(getPingYin(v.getName().trim()));
            BeanUtils.copyProperties(v, user);
            return user;
        }).collect(Collectors.toList());
        userList.forEach(v->{
            LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
            lambda.eq(StringUtils.isNotEmpty(v.getUserid()), User::getUserid,v.getUserid());
            Integer count = userService.selectCount(lambda);
            //不存在 插入
            if (count == 0){
                userService.insert(v);
            }
        });
    }

    /**
     * 递归 分页 去读取 部门下面的用户信息！
     * 递归逐层返回！
     * @param id
     * @param listRes
     * @return
     */
    public List<OapiUserListbypageResponse.Userlist> getUserListInfo(Long id,int index,List<OapiUserListbypageResponse.Userlist> listRes){
        List<OapiUserListbypageResponse.Userlist> list = new ArrayList<>();
        list = getUserListInfo(id, Long.valueOf(index-1)*100L, 100L);
        if (!CollectionUtils.isEmpty(list) && list.size()>0){
            index++;
            getUserListInfo(id,index,listRes);
            listRes.addAll(list);
        }
        return listRes;
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
            globToken = tokenResultVo.getAccessToken();
            printlnJson(rsp.getBody());
            return globToken;
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return globToken;
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


    // 将汉字转换为全拼
    public String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else{
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            // System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

}
