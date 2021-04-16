package org.spring.springboot.getui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.resources.SenderMessages;
import lombok.SneakyThrows;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 对指定用户列表推送消息相关demo
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
@Component
public class PushMessage {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${getui.appid}")
    private  String appid;
    @Value("${getui.appkey}")
    private  String appkey;
    @Value("${getui.mastersecret}")
    private  String mastersecret;
    @Autowired
    private PushTemplate pushTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private IGtPush push = null;

    @PostConstruct
    public  void pingStart(){
        System.setProperty("needOSAsigned", "true"); //java关闭最快域名检测，系统启动参数
        push = new IGtPush(appkey, mastersecret);
    }

    public List sendMessage(List<MessageUserLog> cid){
        long begin=System.currentTimeMillis();
        String taskId = getContentId();
        List list = null;
        try {
            list = pushToList(taskId, cid);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long timeValue = (System.currentTimeMillis() - begin);
        log.info("单批消息耗时：-----" + timeValue + "毫秒");
        return list;
    }


    /**
     * 用于在推送时去查找对应的message
     * @return
     */
    private String getContentId() {
        ListMessage listMessage = getListMessage();
        String contentId = push.getContentId(listMessage);
        return contentId;
    }

    /**
     * 对指定用户列表推送消息
     * 场景1，对于抽奖活动的应用，需要对已知的某些用户推送中奖消息，就可以通过clientid列表方式推送消息。
     * 场景2，向新客用户发放抵用券，提升新客的转化率，就可以事先提取新客列表，将消息指定发送给这部分指定CID用户。
     * @param taskId
     * 返回失败的List
     */
    private List pushToList(String taskId,List<MessageUserLog> cidList) throws JsonProcessingException {
//         System.setProperty("gexin_pushList_needDetails", "true"); //配置返回每个用户返回用户状态，可选
//         System.setProperty("gexin_pushList_needAliasDetails", "true");  // 配置返回每个别名及其对应cid的用户状态，可选
        //失败标识符 。 默认失败 false
        boolean flag = false;
        // 配置推送目标
        List listTarget = new ArrayList();
        cidList.forEach(v->{
            Target target1 = new Target();
            target1.setAppId(appid);
            target1.setClientId(v.getCilentId());
            listTarget.add(target1);
        });
        IPushResult ret = null;
        try {
            ret = push.pushMessageToList(taskId, listTarget);
            /*IPushResult pushResultByTaskidList = push.getPushResult(taskId);
            System.out.println("推送返回结果！--"+objectMapper.writeValueAsString(pushResultByTaskidList.getResponse()));*/
        } catch (Exception e) {
            log.info("发送消息失败，错误信息{}",e.getMessage());
        }
        if (Objects.nonNull(ret)) {
            Map map = ret.getResponse();
            String result = MapUtils.getString(map, "result");
            if (result.equals("ok")){
                flag = true;
                log.info("推送成功，返回结果" + objectMapper.writeValueAsString(map));
            }else{
                log.warn("推送出错，返回结果" + objectMapper.writeValueAsString(map));
            }
        } else {
            log.warn("服务器响应异常");
        }
        //记录入库
        boolean finalFlag = flag;
        cidList.stream().map(v->{
            v.setCount(v.getCount() + 1);//发送 次数 + 1！
            v.setSendFlag(finalFlag?"ok":"error");// 1 成功，0失败
            return v;
        }).forEach(w->{
            try {
                System.out.println("当前线程--" + Thread.currentThread());
                log.info("数据入库！-----"+objectMapper.writeValueAsString(w));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        //如果失败的话  返回这批数据！
        return flag == false?cidList: Lists.newArrayList();
    }

    private ListMessage getListMessage() {
        NotificationTemplate template = pushTemplate.getNotificationTemplate();
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        message.setPushNetWorkType(0);//判断客户端是否wifi环境下推送。1为仅在wifi环境下推送，0为不限制网络环境，默认不限
        // 厂商下发策略；1: 个推通道优先，在线经个推通道下发，离线经厂商下发(默认);2: 在离线只经厂商下发;3: 在离线只经个推通道下发;4: 优先经厂商下发，失败后经个推通道下发;
        message.setStrategyJson("{\"default\":4,\"ios\":4,\"st\":4}");
        return message;
    }

    /**
     * 任务组名推送, 一个应用同时下发了n个推送任务，为了更好地跟踪这n个任务的推送效果，可以把他们组成一个任务组，在查询数据时，只需要输入该任务组名即可同时查到n个任务的数据结果。
     * @param groupName
     * @return
     */
    private String getContentIdOfGroupName(String groupName) {
        return push.getContentId(getListMessage(), groupName);
    }

    /**
     * 对正处于推送状态，或者未接收的消息停止下发
     * @param taskId
     */
    private void stopTask(String taskId) {
        boolean ret = push.stop(taskId);
        System.out.println(ret);
    }
}
