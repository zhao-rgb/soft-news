package com.soft1851.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhao
 * @className CloudConfig
 * @Description TODO
 * @Date 2020/11/23
 * @Version 1.0
 **/
@Configuration
public class CloudConfig {
    public CloudConfig() {
    }

    /**
     * 基于OkHttp3配置的RestTemplate
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
