package com.syigua.services.models.impl;

import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import com.syigua.dto.QwenModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QwenModelServiceImplTest {

    @Autowired
    private QwenModelServiceImpl qwenModelService;

    @Test
    void getRes() {

        ModelResponse response = qwenModelService.getRes("命运是啥？");

        assertEquals(200, response.getCode());

        System.out.println(response.getResponse());
    }



}
