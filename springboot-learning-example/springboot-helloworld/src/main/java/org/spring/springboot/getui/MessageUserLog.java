package org.spring.springboot.getui;

import java.io.Serializable;

/**
 * 发送消息记录表
 * @author zhonglin
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1611:26
 */
public class MessageUserLog implements Serializable {
    private Long id;
    private Long userId;
    private String userName;
    private String cilentId;
    private Long messageId;
    private Integer count;
    private String sendFlag;

    public MessageUserLog() {
        super();
    }

    public MessageUserLog(Long id, Long userId, String userName, String cilentId, Long messageId, Integer count, String sendFlag) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.cilentId = cilentId;
        this.messageId = messageId;
        this.count = count;
        this.sendFlag = sendFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCilentId() {
        return cilentId;
    }

    public void setCilentId(String cilentId) {
        this.cilentId = cilentId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }
}
