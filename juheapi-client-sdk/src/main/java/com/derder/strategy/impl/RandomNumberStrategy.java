package com.derder.strategy.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.derder.client.JuHeApiClient;
import com.derder.constant.MyUrl;
import com.derder.strategy.BaseStrategy;
import com.derder.strategy.StrategyFactory;
import com.derder.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RandomNumberStrategy implements BaseStrategy, InitializingBean {

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
        response= HttpRequest.get(GATEWAY_HOST + restfulUrl)
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
     * @params body 请求头参数
     * @return 请求头Map
     */
    private Map<String, String> getHeaderMap(String body) {
        HashMap<String, String> keyMap = new HashMap<>();
        keyMap.put("accessKey", apiClient.getAccessKey());
        keyMap.put("nonce", RandomUtil.randomNumbers(4));
        try {
            keyMap.put("body", URLEncoder.encode(body, "utf8"));
        } catch (Exception e) {
            log.error("加密传递参数出错,异常信息为: " + e);
            throw new RuntimeException("加密传递参数出错");
        }
        keyMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        keyMap.put("sign", SignUtil.genSign(body, apiClient.getSecretKey()));
        return keyMap;
    }

    private <T> String getJson(T params) {
        return JSONUtil.toJsonStr(params);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("/api/randomNumber", this);
    }
}
