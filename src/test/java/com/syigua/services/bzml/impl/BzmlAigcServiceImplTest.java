package com.syigua.services.bzml.impl;

import com.syigua.params.bzml.BzmlParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BzmlAigcServiceImplTest {

    @Autowired
    private BzmlAigcServiceImpl bzmlAigcService;

    @Test
    void aigcBzml() {
        BzmlParams bzmlParams = new BzmlParams();
        bzmlParams.setGender("男");
        bzmlParams.setBirthYear("1999");
        bzmlParams.setBirthMonth("12");
        bzmlParams.setBirthDay("12");
        bzmlParams.setBirthHour("10");
        boolean bn = bzmlAigcService.aigcBzml(bzmlParams);
        assertEquals(bn, true);
    }

}