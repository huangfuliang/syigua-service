package com.syigua.services.xmx.impl;

import com.alibaba.fastjson.JSONObject;
import com.syigua.dto.ModelResponse;
import com.syigua.po.SygPromptPO;
import com.syigua.services.models.QwenModelsService;
import com.syigua.services.models.SygPromptService;
import com.syigua.services.xmx.XmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class XmxServiceImpl implements XmxService {

    @Autowired
    private QwenModelsService qwenModelsService;

    @Autowired
    private SygPromptService sygPromptService;

    private final static Integer PROMPT_CODE_BZ = 8008;

    @Override
    public String getYs(String content) {
        SygPromptPO sygPromptPO = sygPromptService.getPromptByCode(PROMPT_CODE_BZ);
        Map<String, String> map = new HashMap<>();
        map.put("zw_content", content);
        String smxPrompt = wrapPrompt(map, sygPromptPO.getPrompt());
        ModelResponse response = qwenModelsService.getXms(smxPrompt);
        JSONObject choices = JSONObject.parseObject(response.getResponse());
        JSONObject choice = choices.getJSONArray("choices").getJSONObject(0);
        return choice.getJSONObject("message").getString("content");
    }

    private String wrapPrompt(Map<String, String> map, final String sygPrompt) {
        return map.entrySet().stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .reduce(sygPrompt, (prompt, entry) -> prompt.replaceAll(entry.getKey(), entry.getValue()),
                        (prompt1, prompt2) -> prompt1.replaceAll(prompt2, ""));
    }
}
