package com.derder.strategy.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.derder.client.JuHeApiClient;
import com.derder.constant.MyUrl;
import com.derder.strategy.StrategyFactory;
import com.derder.strategy.BaseStrategy;
import com.derder.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ${strategyname} implements BaseStrategy {
    private static final String GATEWAY_HOST = MyUrl.GATEWAY_HOST;

    @Autowired
    private JuHeApiClient apiClient;

    @Override
    public String handlerRequest(String restfulUrl, String params) {
        log.info("url = {} , paramss = {} ", restfulUrl, params);
        String json = null;
        // 对请求参数进行json化处理
        if (params == null){
            params = " ";
            json = " ";
        }
        json = getJson(params);
        HttpResponse response = null;
        // 向网关发送请求
        response=HttpRequest.<#if method=="GET">get<#elseif method=="POST">post</#if>(GATEWAY_HOST + restfulUrl)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute()
                .charset(StandardCharsets.UTF_8);
        String result = response.body();
        log.info("SDK 返回状态为: {}", response.getStatus());
        log.info("SDK 返回结果为: {}", result);
        return result;
    }


    /**
    * 封装请求头Map
    * params body 请求头参数
    * @return 请求头Map
    */
    private Map<String, String> getHeaderMap(String body) {
        HashMap<String, String> keyMap = new HashMap<>();
        // 添加请求头参数
        keyMap.put("accessKey", apiClient.getAccessKey());
        // 添加随机数
        keyMap.put("nonce", RandomUtil.randomNumbers(4));
        try {
            keyMap.put("body", URLEncoder.encode(body, "utf8"));
        } catch (Exception e) {
            log.error("加密传递参数出错,异常信息为: " + e);
            throw new RuntimeException("加密传递参数出错");
        }
        // 添加时间戳 防止请求重发
        keyMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 添加签名 防止修改请求参数
        keyMap.put("sign", SignUtil.genSign(body, apiClient.getSecretKey()));
        return keyMap;
    }

    /**
    * 参数json化
    * @param params 参数
    * @return String
    */
    private <T> String getJson(T params) {
        return JSONUtil.toJsonStr(params);
    }


    @PostConstruct
    public void init() {
        StrategyFactory.register(MyUrl.${urlname}, this);
        log.info("Strategy registered for {}",MyUrl.${urlname});
    }
}
