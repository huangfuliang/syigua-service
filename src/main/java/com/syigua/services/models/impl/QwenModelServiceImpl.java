package com.syigua.services.models.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syigua.dto.ChatHistoryParams;
import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import com.syigua.dto.QwenModel;
import com.syigua.mapper.WxMsgMapper;
import com.syigua.po.WxMsgPO;
import com.syigua.services.models.QwenModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
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

    @Autowired
    private WxMsgMapper wxMsgMapper;

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

    public ModelResponse getResByHistory(String prompt, String userName) {
        List<WxMsgPO> wxMsgs = wxMsgMapper.getContentByUserName(userName);
        long startTime = System.currentTimeMillis();
        Queue<ChatHistoryParams> history  = convertToQueue(wxMsgs);
        JSONObject body = getRequestBodyWithHistory(prompt, history);
        System.out.println(body);
        System.out.println(body.toJSONString());
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(qwenModel.getUrl(), requestEntity, String.class);
        ModelResponse res = new ModelResponse();
        res.setCode(response.getStatusCode().value());
        res.setResponse(response.getBody());
        System.out.println("请求耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        return res;
    }

    /**
     * 构建包含历史对话的请求体
     * @param prompt 当前提问参数
     * @param history 历史对话队列
     * @return 返回请求体数据
     */
    private JSONObject getRequestBodyWithHistory(String prompt, Queue<ChatHistoryParams> history) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", qwenModel.getModel());
        JSONArray messages = new JSONArray();
        // 添加历史对话
        for (ChatHistoryParams params : history) {
            if (Objects.nonNull(params.getRole())) {
                JSONObject message = new JSONObject();
                message.put("role", params.getRole());
                message.put("content", params.getContent());
                messages.add(message);
            }
        }
        // 添加当前提问
        JSONObject currentMessage = new JSONObject();
        currentMessage.put("role", "user");
        currentMessage.put("content", prompt);
        messages.add(currentMessage);
        requestBody.put("messages", messages);
        return requestBody;
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

    private Queue<ChatHistoryParams> convertToQueue(List<WxMsgPO> history) {
        Queue<ChatHistoryParams> queue = new LinkedList<>();
        for (WxMsgPO msg : history) {
            ChatHistoryParams params = new ChatHistoryParams();
            params.setRole(msg.getRole());
            params.setContent(msg.getContent());
            queue.add(params);
        }
        return queue;
    }


}
