package com.syigua.services.gylq.impl;

import com.syigua.mapper.SygGylqQwMapper;
import com.syigua.po.SygGylqQw;
import com.syigua.services.gylq.SygGylqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SygGylqServiceImpl implements SygGylqService {

    @Autowired
    private SygGylqQwMapper sygGylqQwMapper;

    @Override
    public String getQwByTjlx(String tjlx) {
        SygGylqQw sygGylqQw = sygGylqQwMapper.getQwByTjlx(tjlx);
        return sygGylqQw.getXj() + " " + sygGylqQw.getGw() + " - " + sygGylqQw.getQw();
    }
}
