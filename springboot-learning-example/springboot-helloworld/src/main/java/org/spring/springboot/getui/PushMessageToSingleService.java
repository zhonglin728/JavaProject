package org.spring.springboot.getui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.Constants;
import com.gexin.rp.sdk.http.IGtHttpProxy;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


/**
 * 单推和批量单推相关demo
 *
 * @author zhonglin
 * @see
 * @since 2019-07-09
 */
@Component
public class PushMessageToSingleService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${getui.appid}")
    private  String appid;
    @Value("${getui.appkey}")
    private  String appkey;
    @Value("${getui.mastersecret}")
    private  String mastersecret;
    @Autowired
    private PushTemplate pushTemplate;


    private IGtPush push = null;

    @PostConstruct
    public  void pingStart(){
        System.setProperty("needOSAsigned", "true"); //java关闭最快域名检测，系统启动参数
        // 返回别名对应的每个cid的推送详情
        System.setProperty(Constants.GEXIN_PUSH_SINGLE_ALIAS_DETAIL, "true");
        push = new IGtPush(appkey, mastersecret);
        new IGtHttpProxy();
    }
    public static void main(String[] args) {

        //pushToSingle();
//        pushToSingleBatch();
    }

    /**
     * 对单个用户推送消息
     * <p>
     * 场景1：某用户发生了一笔交易，银行及时下发一条推送消息给该用户。
     * <p>
     * 场景2：用户定制了某本书的预订更新，当本书有更新时，需要向该用户及时下发一条更新提醒信息。
     * 这些需要向指定某个用户推送消息的场景，即需要使用对单个用户推送消息的接口。
     */
    public MessageUserLog pushToSingle(MessageUserLog messageUserLog) {
        if(Objects.nonNull(messageUserLog)){//推送三次 不在执行推送。
            if (messageUserLog.getCount() < 3 ){
                if (messageUserLog.getSendFlag().equals("none") || messageUserLog.getSendFlag().equals("error"))
                pushToSingle(sendMessage(messageUserLog));
            }

        }
       return messageUserLog;
    }
    private MessageUserLog sendMessage(MessageUserLog messageUserLog){
        long begin=System.currentTimeMillis();
        AbstractTemplate template = pushTemplate.getNotificationTemplate(); //通知模板(点击后续行为: 支持打开应用、发送透传内容、打开应用同时接收到透传 这三种行为)
//        AbstractTemplate template = PushTemplate.getLinkTemplate(); //点击通知打开(第三方)网页模板
//        AbstractTemplate template = PushTemplate.getTransmissionTemplate(); //透传消息模版
//        AbstractTemplate template = PushTemplate.getRevokeTemplate(); //消息撤回模版
//        AbstractTemplate template = PushTemplate.getStartActivityTemplate(); //点击通知, 打开（自身）应用内任意页面

        // 单推消息类型
        SingleMessage message = getSingleMessage(template);
        Target target = new Target();
        target.setAppId(appid);
        target.setClientId(messageUserLog.getCilentId());
//        target.setAlias(ALIAS); //别名需要提前绑定
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            log.info("推送出错"+ e.getMessage());
            /*e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());*/
        }
        String result = MapUtils.getString(ret.getResponse(),"result");
        if (Objects.nonNull(ret) && "ok".equals(result)){
            messageUserLog.setSendFlag("ok");//成功标识符。
            log.info("推送成功返回" + ret.getResponse());
        }else{
            messageUserLog.setSendFlag("error");//失败标识符。
            log.info("推送失败返回" + ret.getResponse());
        }
        messageUserLog.setCount(messageUserLog.getCount() + 1);
        long timeValue = (System.currentTimeMillis() - begin);
        log.info("单消息耗时：-----" + timeValue + "毫秒");
        //失败后返回当前数据！
        return messageUserLog;
    }


    private SingleMessage getSingleMessage(AbstractTemplate template) {
        SingleMessage message = new SingleMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(72 * 3600 * 1000);
        // 判断客户端是否wifi环境下推送。1为仅在wifi环境下推送，0为不限制网络环境，默认不限
        //message.setPushNetWorkType(1);
        //message.setPushNetWorkType(1); // 判断客户端是否wifi环境下推送。1为仅在wifi环境下推送，0为不限制网络环境，默认不限
        // 厂商下发策略；1: 个推通道优先，在线经个推通道下发，离线经厂商下发(默认);2: 在离线只经厂商下发;3: 在离线只经个推通道下发;4: 优先经厂商下发，失败后经个推通道下发;
        message.setStrategyJson("{\"default\":4,\"ios\":4,\"st\":4}");
        return message;
    }

}
