package com.derder.client;

import com.derder.strategy.StrategyContext;
import com.derder.strategy.StrategyFactory;
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

    public String getResult(String restfulUrl, String params) {
        StrategyContext context = new StrategyContext();
        context.setStrategy(StrategyFactory.getStrategy(restfulUrl));
        return context.executeStrategy(restfulUrl, params);
    }
}
