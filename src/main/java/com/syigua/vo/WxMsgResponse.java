package com.syigua.vo;

import lombok.Data;

@Data
public class WxMsgResponse {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Content;

    public String getResponseXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(ToUserName).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(FromUserName).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(CreateTime).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[").append(MsgType).append("]]></MsgType>");
        sb.append("<Content><![CDATA[").append(Content).append("]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }

}
