package com.derder;

import com.derder.apiservice.impl.ApiServiceImpl;
import com.derder.client.JuHeApiClient;
import com.derder.strategy.BaseContext;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("juheapi.client")
@Data
@ComponentScan
public class JuHeApiConfig {
    private String accessKey;

    private String secretKey;


    @Bean
    public BaseContext baseContext() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.setApiClient(new JuHeApiClient(accessKey, secretKey));
        BaseContext baseContext = new BaseContext();
        baseContext.setApiClient(apiService);
        return baseContext;
    }
}
