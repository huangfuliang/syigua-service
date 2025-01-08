package com.syigua.services.models.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import com.syigua.dto.QwenModel;
import com.syigua.services.models.QwenModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Queue;

/**
 * @author huangfuliang
 * 千问大模型实现类
 */
@Service
public class QwenModelServiceImpl implements QwenModelsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QwenModel qwenModel;

    private String PROPMT = "你是一个专业的命理师，参考《万年历》、《渊海子平》、《三命通会》、" +
            "《滴天髓》、《子平真诠》、《穷通宝鉴》、《神峰通考》、《星平会海》、《命理约言》、" +
            "《命理约言》、《千里命稿》、《命理探源》的书籍的内容， ";

    public ModelResponse getRes(String prompt) {
        long startTime = System.currentTimeMillis();
        JSONObject body = getRequestBody(PROPMT + prompt);
        System.out.println(body.toJSONString());
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(qwenModel.getUrl(), requestEntity, String.class);
        ModelResponse res = new ModelResponse();
        if (Objects.nonNull(response)) {
            res.setCode(response.getStatusCode().value());
            res.setResponse(response.getBody());
        }
        System.out.println("请求耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        return res;
    }

    public ModelResponse getXms(String prompt) {
        long startTime = System.currentTimeMillis();
        JSONObject body = getRequestBody(prompt);
        System.out.println(body.toJSONString());
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(qwenModel.getUrl(), requestEntity, String.class);
        ModelResponse res = new ModelResponse();
        if (Objects.nonNull(response)) {
            res.setCode(response.getStatusCode().value());
            res.setResponse(response.getBody());
        }
        System.out.println("请求耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        return res;
    }

    /**
     * 构建请求头
     * @return 返回请求头数据
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + qwenModel.getAppKey());
        headers.set("Content-Type", "application/json");
        return headers;
    }

    /**
     * 构建请求体
     * @param prompt 提问参数
     * @return 返回请求体数据
     */
    private JSONObject getRequestBody(String prompt) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", qwenModel.getModel());
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);
        requestBody.put("messages", messages);
        return requestBody;
    }



}
