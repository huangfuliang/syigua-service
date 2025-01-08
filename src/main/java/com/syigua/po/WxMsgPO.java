package com.syigua.po;

import lombok.Data;

@Data
public class WxMsgPO {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String msgType;

    private String content;

    private String msgId;


}
