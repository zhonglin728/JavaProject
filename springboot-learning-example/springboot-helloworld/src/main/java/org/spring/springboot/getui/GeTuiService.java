package org.spring.springboot.getui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.StartActivityTemplate;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 对指定用户列表推送消息相关demo
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
@Service
public class GeTuiService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PushMessage pushMessage;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private PushMessageToSingleService pushMessageToSingleService;
    @Autowired
    private ObjectMapper objectMapper;

    //单条 推送
    public void sendSingle(){
        List<MessageUserLog> list = Lists.newArrayList();
        MessageUserLog messageUserLog1 = new MessageUserLog();
        messageUserLog1.setId(1L);
        messageUserLog1.setUserId(Long.parseLong(String.valueOf(123)));
        messageUserLog1.setCilentId("8f7174ff3ccff0734c38ef2994107788");
        messageUserLog1.setCount(0);//推送次数、
        messageUserLog1.setSendFlag("none");
        list.add(messageUserLog1);

        for (MessageUserLog messageUserLog : list) {
            MessageUserLog me = pushMessageToSingleService.pushToSingle(messageUserLog);
            try {
                log.info("执行update操作-" + objectMapper.writeValueAsString(me));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }





    }


    public void send(){
        List<?> data = mokeData();
        List<? extends List<?>> lists = splitList(data, 500);
        lists.forEach(v->{
                    List list = pushMessage.sendMessage((List<MessageUserLog>) v);
                    if (CollectionUtils.isNotEmpty(list)){
                        //failList.add(list);
                    }
        });
        System.out.println("完成！ ");
    };
    /**
     * 造数据
     * @return
     */
    public List<?> mokeData(){
        List<Object> list = Lists.newArrayList();
        for (int i=0;i<1;i++){
            MessageUserLog messageUserLog1 = new MessageUserLog();
            messageUserLog1.setId(1L);
            messageUserLog1.setUserId(Long.parseLong(String.valueOf(i)));
            messageUserLog1.setCilentId("8f7174ff3ccff0734c38ef29941077881");
            messageUserLog1.setCount(0);
            list.add(messageUserLog1);
            MessageUserLog messageUserLog2 = new MessageUserLog();
            messageUserLog2.setId(2L);
            messageUserLog2.setUserId(Long.parseLong(String.valueOf(i)));
            messageUserLog2.setCilentId("88");
            messageUserLog2.setCount(0);
            list.add(messageUserLog2);
        }
        return list;
    }


    /**
     * 拆分集合
     * @param <T>
     * @param resList  要拆分的集合
     * @param count    每个集合的元素个数
     * @return  返回拆分后的各个集合
     */
    public static  <T> List<List<T>> splitList(List<T> resList,int count){

        if(resList==null ||count<1)
            return  null ;
        List<List<T>> ret=new ArrayList<List<T>>();
        int size=resList.size();
        if(size<=count){ //数据量不足count指定的大小
            ret.add(resList);
        }else{
            int pre=size/count;
            int last=size%count;
            //前面pre个集合，每个大小都是count个元素
            for(int i=0;i<pre;i++){
                List<T> itemList=new ArrayList<T>();
                for(int j=0;j<count;j++){
                    itemList.add(resList.get(i*count+j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if(last>0){
                List<T> itemList=new ArrayList<T>();
                for(int i=0;i<last;i++){
                    itemList.add(resList.get(pre*count+i));
                }
                ret.add(itemList);
            }
        }
        return ret;

    }
}
