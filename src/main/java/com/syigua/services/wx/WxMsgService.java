package com.syigua.services.wx;

import com.syigua.po.WxMsgPO;
import com.syigua.vo.WxMsgResponse;

public interface WxMsgService {

    WxMsgResponse handleMsg(String msg);

}
