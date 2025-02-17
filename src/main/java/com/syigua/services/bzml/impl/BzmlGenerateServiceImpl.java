package com.syigua.services.bzml.impl;


import com.syigua.mapper.BzmlBgMapper;
import com.syigua.params.bzml.BzmlParams;
import com.syigua.po.BzmlBg;
import com.syigua.services.bzml.BzmlGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BzmlGenerateServiceImpl implements BzmlGenerateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BzmlBgMapper bzmlBgMapper;

    /**
     * 大模型调用地址
     */
    private String URL = "http://www.laibuyiguaba.cn:5001/bzml/generate";
    // private String URL = "http://localhost:5001/bzml/generate";

    @Override
    public boolean generateBzml(BzmlParams bzmlParams, String uid) {
        log.info("uid:{}, bzmlParmas: {}", uid, bzmlParams.toString());
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", bzmlParams.toString()); // 假设 BzmlParams 有 getPrompt 方法
        requestBody.put("uid", uid);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建 HttpEntity 对象
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        try {
            // 发送 POST 请求
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
            // 检查响应状态码
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("请求成功，响应内容: {}", response.getBody());

                insertBg(bzmlParams, uid);
                return true;
            } else {
                log.error("请求失败，状态码: {}", response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            log.error("请求发生异常: ", e);
            return false;
        }
    }

    private void insertBg(BzmlParams bzmlParams, String uid) {
        BzmlBg bzmlBg = new BzmlBg();
        bzmlBg.setUid(uid);
        String fileName = String.format("%s的八字报告", bzmlParams.getName());
        bzmlBg.setFileName(fileName);
        bzmlBgMapper.insert(bzmlBg);
    }

    @Override
    public boolean finish(String uid, String fileUrl) {
        BzmlBg bzmlBg = new BzmlBg();
        bzmlBg.setUid(uid);
        bzmlBg.setFileUrl(fileUrl);
        bzmlBgMapper.update(bzmlBg);
        return true;
    }

}
