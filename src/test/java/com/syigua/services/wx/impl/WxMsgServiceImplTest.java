package com.syigua.services.wx.impl;

import com.syigua.vo.WxMsgResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class WxMsgServiceImplTest {

    @Autowired
    private WxMsgServiceImpl wxMsgService;

    @Test
    void handleMsg() {
        String msg = "<xml><ToUserName><![CDATA[gh_5bf5cb0643bb]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oPrff6xC96XrAT76reg87nt_Q7pw]]></FromUserName>\n" +
                "<CreateTime>1736329710</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[张小 爱情]]></Content>\n" +
                "<MsgId>24858265527803719</MsgId>\n" +
                "</xml>";
        WxMsgResponse msgResponse =  wxMsgService.handleMsg(msg);
        System.out.println(msgResponse.getResponseXml());
    }

    @Test
    void handleMsg_gylq_qq() {
        String msg = "<xml><ToUserName><![CDATA[gh_5bf5cb0643bb]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oPrff6xC96XrAT76reg87nt_Q7pw]]></FromUserName>\n" +
                "<CreateTime>1736329710</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[求签]]></Content>\n" +
                "<MsgId>24858265527803719</MsgId>\n" +
                "</xml>";
        WxMsgResponse msgResponse =  wxMsgService.handleMsg(msg);
        System.out.println(msgResponse.getResponseXml());
    }

    @Test
    void handleMsg_gylq_jq() {
        String msg = "<xml><ToUserName><![CDATA[gh_5bf5cb0643bb]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oPrff6xC96XrAT76reg87nt_Q7pw]]></FromUserName>\n" +
                "<CreateTime>1736329710</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[解签]]></Content>\n" +
                "<MsgId>24858265527803719</MsgId>\n" +
                "</xml>";
        WxMsgResponse msgResponse =  wxMsgService.handleMsg(msg);
        System.out.println(msgResponse.getResponseXml());
    }


}