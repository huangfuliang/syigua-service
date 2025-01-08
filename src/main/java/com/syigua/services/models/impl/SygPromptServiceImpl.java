package com.syigua.services.models.impl;

import com.syigua.mapper.SygPromptMapper;
import com.syigua.po.SygPromptPO;
import com.syigua.services.models.SygPromptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SygPromptServiceImpl implements SygPromptService {

    @Resource
    private SygPromptMapper sygPromptMapper;

    @Override
    public SygPromptPO getPromptByCode(Integer code) {
        return sygPromptMapper.getPromptByCode(code);
    }

}
