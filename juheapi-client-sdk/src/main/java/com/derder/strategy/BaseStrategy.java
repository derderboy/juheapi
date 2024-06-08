package com.derder.strategy;

public interface BaseStrategy {
    String handlerRequest(String restfulUrl, String params);
}
