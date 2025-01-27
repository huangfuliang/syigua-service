package com.syigua.services.models.impl;

import com.syigua.dto.ChatHistoryParams;
import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import com.syigua.dto.QwenModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

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


    @Test
    void getResByHistory() {
        ModelResponse modelResponse = qwenModelService.getResByHistory("解签", "oPrff6xC96XrAT76reg87nt_Q7pw");
        System.out.println(modelResponse.getResponse());

    }
}
