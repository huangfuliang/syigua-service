package com.syigua.mapper;

import com.syigua.po.WxMsgPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxMsgMapper {

    void addMsg(WxMsgPO wxMsgPO);

    List<WxMsgPO> getContentByUserName(String userName);

}
