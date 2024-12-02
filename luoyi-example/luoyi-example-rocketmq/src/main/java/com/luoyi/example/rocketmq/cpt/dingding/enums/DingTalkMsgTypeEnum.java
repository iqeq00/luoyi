package com.luoyi.example.rocketmq.cpt.dingding.enums;

public enum DingTalkMsgTypeEnum {

    TEXT("文本", "text"),
    LINK("链接", "link"),
    MARKDOWN("MarkDown", "markdown"),
    ACTIONCARD("ActionCard", "actionCard"),
    FEEDCARD("FeedCard", "feedCard")
    ;

    String name;
    String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    DingTalkMsgTypeEnum(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static DingTalkMsgTypeEnum getEnum(String type) {
        for (DingTalkMsgTypeEnum dingMsgType : DingTalkMsgTypeEnum.values()) {
            if (dingMsgType.getType().equals(type)) {
                return dingMsgType;
            }
        }
        throw new RuntimeException("Error: Invalid DingMsgTypeEnum type value: " + type);
    }
}
