package com.derder;

import com.derder.client.JuHeApiClient;
import com.derder.strategy.BaseContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
public class JiheapiClientSdkApplicationTests {
    @Resource
    private JuHeApiClient juHeApiClient;
    @Test
    void contextLoads() {
        System.out.println(juHeApiClient.getAccessKey());
        System.out.println("lllll");
    }

}
