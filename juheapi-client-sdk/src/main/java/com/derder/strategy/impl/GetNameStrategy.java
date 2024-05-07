package com.derder.strategy.impl;

import com.derder.apiservice.ApiService;
import com.derder.strategy.BaseStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetNameStrategy implements BaseStrategy {
    @Override
    public String handlerRequest(String restfulUrl, String params, ApiService apiService, String method) {
        log.info("url = {} , params = {} , method = {}", restfulUrl, params, method);
        return apiService.definitionRequest(restfulUrl, params, method);
    }
}
