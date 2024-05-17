package com.derder;

import com.derder.client.JuHeApiClient;
import com.derder.strategy.StrategyFactory;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(JuHeApiClient.class)
@ConditionalOnProperty(prefix = "juheapi.client", value = "enable", matchIfMissing = true)
@ConditionalOnClass(StrategyFactory.class)
public class JuHeApiConfig {
    @Resource
    private JuHeApiClient juHeApiClient;
}
