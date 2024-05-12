package com.derder;

import com.derder.apiservice.impl.ApiServiceImpl;
import com.derder.client.JuHeApiClient;
import com.derder.strategy.BaseContext;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(JuHeApiClient.class)
@ConditionalOnProperty(prefix = "juheapi.client", value = "enable", matchIfMissing = true)
@ConditionalOnClass(BaseContext.class)
@Data
public class JuHeApiConfig {
    private String accessKey;

    private String secretKey;

    @Resource
    private JuHeApiClient juHeApiClient;

    @Bean
    public BaseContext baseContext() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.setApiClient(juHeApiClient);
        BaseContext baseContext = new BaseContext();
        baseContext.setApiClient(apiService);
        return baseContext;
    }
}
