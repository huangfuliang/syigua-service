package com.syigua.mapper;

import com.syigua.po.SygWxEtxContentPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SygWxEtxContentMapperTest {

    @Autowired
    private SygWxEtxContentMapper sygWxEtxContentMapper;

    @Test
    void getContentByInput() {
        SygWxEtxContentPO sygWxEtxContentPO = sygWxEtxContentMapper.getContentByInput("求签");
        assertNotNull(sygWxEtxContentPO);
        System.out.println(sygWxEtxContentPO);
    }
}