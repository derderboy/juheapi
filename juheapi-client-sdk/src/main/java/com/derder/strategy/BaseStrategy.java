package com.derder.strategy;

import com.derder.apiservice.ApiService;


public interface BaseStrategy {
    String handlerRequest(String restfulUrl, String params, ApiService apiService, String method);
}
