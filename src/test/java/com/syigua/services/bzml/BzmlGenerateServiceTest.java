package com.syigua.services.bzml;

import com.syigua.params.bzml.BzmlParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BzmlGenerateServiceTest {

   @Autowired
    private BzmlGenerateService bzmlGenerateService;

    @Test
    public void testGenerateBzml() {
        BzmlParams bzmlParams = new BzmlParams();
        bzmlParams.setName("张三");
        bzmlParams.setGender("男");
        bzmlParams.setCalendarType("农历");
        bzmlParams.setBirthdate("1990年12月5日");
        bzmlParams.setQuery("子女");

        bzmlGenerateService.generateBzml(bzmlParams,"1234567");
    }

    @Test
    public void testFinish() {
        bzmlGenerateService.finish("1234567", "http://121");
    }

}