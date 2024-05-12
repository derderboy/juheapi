package com.derder.strategy;

import com.derder.apiservice.ApiService;
import com.derder.apiservice.impl.ApiServiceImpl;
import com.derder.client.JuHeApiClient;
import com.derder.constant.MyUrl;
import com.derder.strategy.impl.GetNameStrategy;
import com.derder.strategy.impl.NoParamsStrategy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略核心上下文类
 * @author derder
 * @version 1.0
 */
@Data
public class BaseContext {
    private static final Map<String, BaseStrategy> strategyMap = new ConcurrentHashMap<>();
    private ApiService apiService;
    private String accessKey;
    private String secretKey;
    private static final NoParamsStrategy NO_PARAMS_STRATEGY = new NoParamsStrategy();
    private static final GetNameStrategy GET_NAME_STRATEGY = new GetNameStrategy();

    static {
        // 获取随机数
        strategyMap.put(MyUrl.RANDOM_NUMBER, NO_PARAMS_STRATEGY);
        // 获取你的名字
        strategyMap.put(MyUrl.GET_YOURNAME, GET_NAME_STRATEGY);
        // 获取随机卡通动物图片
        strategyMap.put(MyUrl.GET_ANIMALIMAGE, NO_PARAMS_STRATEGY);

    }


    public String handler(String restfulUrl, String params, String method) {
        BaseStrategy baseStrategy = strategyMap.get(restfulUrl);
        return baseStrategy.handlerRequest(restfulUrl, params, apiService, method);
    }

    public void setApiClient(ApiService apiService) {
        this.apiService = apiService;
    }

}
