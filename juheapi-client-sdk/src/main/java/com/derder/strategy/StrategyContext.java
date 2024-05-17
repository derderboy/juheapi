package com.derder.strategy;

public class StrategyContext {
    private BaseStrategy strategy;

    public void setStrategy(BaseStrategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String restfulUrl, String params) {
        if (strategy == null) {
            throw new IllegalStateException("No strategy found for URL: " + restfulUrl);
        }
        return strategy.handlerRequest(restfulUrl, params);
    }
}
