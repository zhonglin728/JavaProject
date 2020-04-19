package com.dingding.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingding.domain.User;
import com.dingding.mapper.UserMapper;
import com.dingding.service.DingService;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Scheduled注解可以控制方法定时执行，其中有三个参数可选择：
 *
 * 1、fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，并间隔给定的时间后，执行下一次。
 *
 * 2、fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
 *
 * 3、cron表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。
 * @author zhonglin
 */
@Component
@Slf4j
public class DingScheduledTask {

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
    @Autowired
    private UserMapper userMapper;



    /**
     * @Scheduled(fixedDelay  = 1000*60*60)
     * 每间隔一小时获取Token 防止过期。
     */
    @Scheduled(fixedRate  = 1000*60*60)
    public void getTokenScheduled(){
        dingService.getToken();
        log.info("间隔一小时刷新token!");
    }

    /**
     * 测试发送私人消息！
     */
   /*@Scheduled(fixedRate  = 1000*5)
    public void taskTest(){
        dingService.sendToConversationText(agentId, zlUserId, DateUtils.getStringToday());
    }*/

    /**
     * 定时任务 每周一凌晨0.0 去钉钉拉取用户数据到本地库！
     */
    @Scheduled(cron = "0 0 0 * * 1")
    public void task(){
        dingService.syncUser();
    }

    /**
     * 模板1 发送全体员工
     * 工作日17:25
     */
    @Scheduled(cron = "0 25 17 * * 1,2,3,4,5")
    public void task1(){
        String content = readTemplateToString("1报工全通知.ftl", Maps.newHashMap());
        dingService.sendToConversationTextAll(agentId, content);
    }

    /**
     * 模板2 发送全体员工
     * 周五17:25
     */
    @Scheduled(cron = "0 25 17 * * 5")
    public void task2(){
        String content = readTemplateToString("2报工全通知周末.ftl", Maps.newHashMap());
        dingService.sendToConversationTextAll(agentId, content);
    }

    /**
     * 模板3 发送个人
     * 工作日20:45
     */
    @Scheduled(cron = "0 45 20 * * 1,2,3,4,5")
    //@Scheduled(fixedRate  = 1000*60*60)
    public void task3(){
        String content = readTemplateToString("3报工不符合通知.ftl", Maps.newHashMap());
        List<Map<String, ?>> users = userMapper.getTaskOauth3();
        sendMsg(users, content);

    }

    /**
     * 模板4 发送到个人
     * 周五20:45
     */
    @Scheduled(cron = "0 45 20 * * 5")
    public void task4(){
        String content = readTemplateToString("4报工不符合通知周末.ftl", Maps.newHashMap());
        List<Map<String, ?>> users = userMapper.getTaskOauth4();
        sendMsg(users, content);

    }

    /**
     * 模板5 发送到个人
     * 周日10:45
     */
    @Scheduled(cron = "0 45 10 * * 7")
    public void task5(){
        String content = readTemplateToString("5报工不符合通知周日.ftl", Maps.newHashMap());
        List<Map<String, ?>> users = userMapper.getTaskOauth5();
        sendMsg(users, content);

    }


    /**
     * 模板6 发送到 报工审理组
     * 周六10:30
     */
    //@Scheduled(fixedRate  = 1000*60*60*60)
    @Scheduled(cron = "0 30 10 * * 6")
    public void task6(){
        String content = readTemplateToString("6报工审批全通知.ftl", Maps.newHashMap());
        List<OapiDepartmentListResponse.Department> deptList = dingService.getDeptList();
        OapiDepartmentListResponse.Department department = deptList.stream().filter(v -> "报工审批组".equals(v.getName())).findFirst()
                .orElseThrow(() -> new IllegalStateException("未找到该部门"));
        // 报工审批组发送消息
        dingService.sendToConversationTextDept(String.valueOf(department.getId()),content);

    }

    /**
     * 模板7 发送到个人
     * 周日10:30
     */
    @Scheduled(cron = "0 30 10 * * 7")
    public void task7(){
        String content = readTemplateToString("7报工审批不符合通知.ftl", Maps.newHashMap());
        List<Map<String, ?>> users = userMapper.getTaskOauth7();
        sendMsg(users, content);

    }





    /**
     *  读取模板文件返回字符串！
     * @param ftlName 模板名称
     * @param contengMap 模板需要写入的值
     * @return
     */
    public String readTemplateToString(String ftlName, Map contengMap){
        configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
        try {
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(ftlName), contengMap);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
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
        list = dingService.getUserListInfo(id, Long.valueOf(index-1)*2L, 2L);
        if (!CollectionUtils.isEmpty(list) && list.size()>0){
            index++;
            getUserListInfo(id,index,listRes);
            listRes.addAll(list);
        }
        return listRes;
    }

    /**
     * 发送消息！
     * @param users jira系统查询的数据
     * @param content  模板内容
     */
    public void sendMsg(List<Map<String, ?>> users,String content){
        List<String> author = users.stream().map(v -> {
            return String.valueOf(v.get("author"));
        }).distinct().collect(Collectors.toList());
        //TODO jira数据和本地库NameEn 对比查询出userId
        author.forEach(v->{
            User user = userMapper.selectList(new QueryWrapper<User>().lambda().eq(StringUtils.isNotEmpty(v), User::getNameEn, v))
                    .stream()
                    .findFirst()
                    .orElse(new User().setUserid(""));
            dingService.sendToConversationText(agentId, user.getUserid(),content);
            log.info("用户:{}发送消息，内容是:{}",v,content);
        });
    }
    /**
     * 测试阶段给指定人每隔1分钟执行一次！
     */
    /*@Scheduled(fixedDelay  = 1000*60)
    public void send_to_conversationTextScheduled2(){
        dingService.send_to_conversationText(agentId,zlUserId, "现在时间："+ DateUtils.getStringToday()+"请及时打卡！");
    }*/




}
