package com.syigua.services.gylq.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SygGylqServiceImplTest {

    @Autowired
    private SygGylqServiceImpl sygGylqService;

    @Test
    public void testGetQwByTjlx() {
        String tjlx = null;
        String qw = sygGylqService.getQwByTjlx(tjlx);
        assertNotNull(qw);
        System.out.println(qw);
    }


    @Test
    void getJqByQw() {

        String str = sygGylqService.getJqByQw("解签", "oPrff6xC96XrAT76reg87nt_Q7pw");
        System.out.println(str);

    }
}