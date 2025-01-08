package com.syigua.services.wx.impl;

import com.syigua.dto.ModelResponse;
import com.syigua.mapper.WxMsgMapper;
import com.syigua.po.WxMsgPO;
import com.syigua.services.models.QwenModelsService;
import com.syigua.services.wx.WxMsgService;
import com.syigua.services.xmx.XmxService;
import com.syigua.util.XmlParser;
import com.syigua.vo.WxMsgResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxMsgServiceImpl implements WxMsgService {

    @Autowired
    private WxMsgMapper wxMsgMapper;

    @Autowired
    private XmxService xmxService;



    @Override
    public WxMsgResponse handleMsg(String msg) {
        log.info("handleMsg:{}", msg);
        WxMsgPO wxMsgPO = new WxMsgPO();
        XmlParser xmlParser = new XmlParser();
        xmlParser.buildDoc(msg);
        wxMsgPO.setMsgType(xmlParser.getValueByKey("MsgType"));
        wxMsgPO.setMsgId(xmlParser.getValueByKey("MsgId"));
        wxMsgPO.setContent(xmlParser.getValueByKey("Content"));
        wxMsgPO.setToUserName(xmlParser.getValueByKey("ToUserName"));
        wxMsgPO.setFromUserName(xmlParser.getValueByKey("FromUserName"));
        wxMsgPO.setCreateTime(xmlParser.getValueByKey("CreateTime"));
        // 保存日志数据
        addMsg(wxMsgPO);

        WxMsgResponse response = new WxMsgResponse();
        // 查询内容
        String reqContent = wxMsgPO.getContent();
        String resContent  = xmxService.getYs(reqContent);

        // 拼接微信回复参数
        response.setContent(resContent);
        response.setMsgType(wxMsgPO.getMsgType());
        response.setFromUserName(wxMsgPO.getFromUserName());
        response.setToUserName(wxMsgPO.getToUserName());
        response.setCreateTime(System.currentTimeMillis() / 1000);
        return response;
    }

    @Async
    public void addMsg(WxMsgPO wxMsgPO) {
        wxMsgMapper.addMsg(wxMsgPO);
    }

}
