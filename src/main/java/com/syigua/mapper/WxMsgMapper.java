package com.syigua.mapper;

import com.syigua.po.WxMsgPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxMsgMapper {

    void addMsg(WxMsgPO wxMsgPO);

}
