package com.derder.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 策略核心上下文类
 * @author derder
 * @version 1.0
 */
public class StrategyFactory {
    private static final Map<String, BaseStrategy> strategyMap = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(StrategyFactory.class.getName());

    public static BaseStrategy getStrategy(String url) {
        BaseStrategy strategy = strategyMap.get(url);
        if (strategy == null) {
            logger.warning("No strategy found for URL: " + url);
        }
        return strategy;
    }

    //注册器，spring启动时会将所有策略注册进去
    public static void register(String url, BaseStrategy baseStrategy){
        logger.info("Registering strategy for URL: " + url);
        strategyMap.put(url, baseStrategy);
        logger.info("Strategy registered successfully for URL: " + url);
    }

}
