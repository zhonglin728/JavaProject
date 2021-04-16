package org.spring.springboot.getui;

import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style6;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 推送样式
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
@Component
public class PushStyle {


    /**
     * Style0 系统样式
     * @link http://docs.getui.com/getui/server/java/template/ 查看效果
     * @return
     */
    public AbstractNotifyStyle getStyle0() {
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("Java代码测试标题"+new Date().getTime());
        style.setText("Java带啊测试内容"+ UUID.randomUUID());
        // 配置通知栏图标
        style.setLogo("icon.png"); //配置通知栏图标，需要在客户端开发时嵌入，默认为push.png
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 配置自定义铃声(文件名，不需要后缀名)，需要在客户端开发时嵌入后缀名为.ogg的铃声文件
        style.setRingName("sound");
        // 角标, 必须大于0, 个推通道下发有效; 此属性目前仅针对华为 EMUI 4.1 及以上设备有效
        style.setBadgeAddNum(1);

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        return style;
    }

    /**
     * Style6 展开式通知样式
     * @link http://docs.getui.com/getui/server/java/template/ 查看效果
     * @return
     */
    public AbstractNotifyStyle getStyle6() {
        Style6 style = new Style6();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png"); //配置通知栏图标，需要在客户端开发时嵌入
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 三种方式选一种
        style.setBigStyle1("bigImageUrl"); //设置大图+文本样式
//        style.setBigStyle2("bigText"); //设置长文本+文本样式

        // 配置自定义铃声(文件名，不需要后缀名)，需要在客户端开发时嵌入后缀名为.ogg的铃声文件
        style.setRingName("sound");
        // 角标, 必须大于0, 个推通道下发有效; 此属性目前仅针对华为 EMUI 4.1 及以上设备有效
        style.setBadgeAddNum(1);
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        return style;
    }
}
