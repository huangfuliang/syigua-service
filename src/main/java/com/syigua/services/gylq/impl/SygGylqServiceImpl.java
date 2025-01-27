package com.syigua.services.gylq.impl;

import com.syigua.dto.ChatHistoryParams;
import com.syigua.mapper.SygGylqQwMapper;
import com.syigua.mapper.WxMsgMapper;
import com.syigua.po.SygGylqQw;
import com.syigua.po.WxMsgPO;
import com.syigua.services.gylq.SygGylqService;
import com.syigua.services.models.QwenModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class SygGylqServiceImpl implements SygGylqService {

    @Autowired
    private SygGylqQwMapper sygGylqQwMapper;

    @Autowired
    private QwenModelsService qwenModelsService;

    @Override
    public String getQwByTjlx(String tjlx) {
        SygGylqQw sygGylqQw = sygGylqQwMapper.getQwByTjlx(tjlx);
        return sygGylqQw.getXj() + " " + sygGylqQw.getGw() + " - " + sygGylqQw.getQw();
    }

    @Override
    public String getJqByQw(String contentQw, String userName) {
        String prompt = "请根据历史的问题和签文，进行解签。" + contentQw;
        qwenModelsService.getResByHistory(prompt, userName);
        return null;
    }

}
