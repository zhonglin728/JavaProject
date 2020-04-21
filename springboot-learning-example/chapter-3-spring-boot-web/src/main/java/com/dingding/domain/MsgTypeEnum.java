package com.dingding.domain;

public enum MsgTypeEnum {
    TEXT("text" , 1),//文本消息
    IMAGE("image" , 2),//图片消息
    VOICE("voice" , 3),//语音消息
    FILE("file" , 4),//文件消息
    LINK("link" , 5),//链接消息
    OA("oa" , 6),//OA消息
    MARKDOWN("markdown" , 7),//markdown消息
    ACTION_CARD("action_card" , 8);//卡片消息

    private String type;
    private int index;

    MsgTypeEnum() {
    }

    MsgTypeEnum(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "MsgTypeEnum{" +
                "type='" + type + '\'' +
                ", index=" + index +
                '}';
    }
}
