package com.derder;

import com.derder.client.JuHeApiClient;
import com.derder.constant.MyUrl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
class JuheapiBackendApplicationTests {
@Resource
private JuHeApiClient juHeApiClient;
    @Test
    public void s(){
        System.out.println(juHeApiClient.getResult(MyUrl.Get_Food, ""));

    }
}
