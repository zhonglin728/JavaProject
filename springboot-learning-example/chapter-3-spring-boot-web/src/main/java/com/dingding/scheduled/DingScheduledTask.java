package com.dingding.scheduled;

import com.dingding.service.DingService;
import com.dingding.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    /**
     * 每间隔一小时获取Token 防止过期。
     */
    @Scheduled(fixedDelay  = 1000*60*60)
    public void getTokenScheduled(){
        dingService.gettoken();
    }
    /**
     * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
     */
   /* @Scheduled(cron = "0 15 10 ? * MON-FRI")
    public void send_to_conversationTextScheduled(){
        dingService.send_to_conversationText(agentId,zlUserId, "现在时间："+ DateUtils.getStringToday()+"请及时打卡！");
    }*/

    /**
     * 测试阶段每隔1分钟执行一次！
     */
    @Scheduled(fixedDelay  = 1000*60)
    public void send_to_conversationTextScheduled2(){
        dingService.send_to_conversationText(agentId,zlUserId, "现在时间："+ DateUtils.getStringToday()+"请及时打卡！");
    }
}
