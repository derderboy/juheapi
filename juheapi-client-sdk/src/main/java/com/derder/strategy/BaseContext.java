package com.derder.strategy;

import com.derder.apiservice.ApiService;
import com.derder.constant.MyUrl;
import com.derder.strategy.impl.GetNameStrategy;
import com.derder.strategy.impl.NoParamsStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略核心上下文类
 * @author derder
 * @version 1.0
 */
public class BaseContext {
    private static final Map<String, BaseStrategy> strategyMap = new ConcurrentHashMap<>();
    private ApiService apiService;
    private static final NoParamsStrategy NO_PARAMS_STRATEGY = new NoParamsStrategy();
    private static final GetNameStrategy GET_NAME_STRATEGY = new GetNameStrategy();

    static {
        // 获取随机数
        strategyMap.put(MyUrl.RANDOM_NUMBER, NO_PARAMS_STRATEGY);
        // 获取你的名字
        strategyMap.put(MyUrl.GET_YOURNAME, GET_NAME_STRATEGY);
        // 获取随机卡通动物图片
        strategyMap.put(MyUrl.GET_ANIMALIMAGE, NO_PARAMS_STRATEGY);


        strategyMap.put(MyUrl.RANDOM_SCENERY, NO_PARAMS_STRATEGY);
        strategyMap.put(MyUrl.MO_YU, NO_PARAMS_STRATEGY);
        strategyMap.put(MyUrl.TALK_LOVE, NO_PARAMS_STRATEGY);
        strategyMap.put(MyUrl.RANDOM_WALLPAPER, NO_PARAMS_STRATEGY);

    }

    public String handler(String restfulUrl, String params, String method) {
        BaseStrategy baseStrategy = strategyMap.get(restfulUrl);
        return baseStrategy.handlerRequest(restfulUrl, params, apiService, method);
    }

    public void setApiClient(ApiService apiService) {
        this.apiService = apiService;
    }
}
