package com.derder.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 调用第三方接口的客户端
 */
@Data
@ConfigurationProperties("juheapi.client")
public class JuHeApiClient {
    private String accessKey;

    private String secretKey;


    public JuHeApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public JuHeApiClient() {}
}
