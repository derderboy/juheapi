package com.derder.juheapigateway;

import com.derder.service.InnerIpBlackService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@EnableDubbo
public class BlackIpListMQ {
    public Set<Object> blackIpList = new HashSet<>();
    private final Object lock = new Object(); // 创建一个锁对象

    @DubboReference
    private InnerIpBlackService innerIpBlackService;
    @RabbitListener(queues = "ipBlack.queue")
    public void setBlackIpList(String message) {
        synchronized (lock) { // 同步代码块
            blackIpList.clear();
            blackIpList.addAll(innerIpBlackService.getBlackIpList());
            if(blackIpList.isEmpty()){
                blackIpList.add("");
            }
        }
    }
    public boolean isBlackListed(String ip) {
        synchronized (lock) { // 同步代码块
            return blackIpList.contains(ip);
        }
    }
}
