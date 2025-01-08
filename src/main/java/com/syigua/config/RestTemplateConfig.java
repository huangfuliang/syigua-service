package com.syigua.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import java.time.Duration;

/**
 * @author huangfuliang
 *  配置RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(3000))
                .setReadTimeout(Duration.ofSeconds(3000))
                .additionalMessageConverters(new MappingJackson2XmlHttpMessageConverter())
                .uriTemplateHandler(new DefaultUriTemplateHandler())
                .build();
    }
}
