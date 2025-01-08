package com.syigua.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class QwenModel {

    @Value("${models.qwen.model}")
    private String model;

    @Value("${models.qwen.url}")
    private String url;

    @Value("${models.qwen.method}")
    private String method;

    @Value("${models.qwen.api_key}")
    private String appKey;

}
