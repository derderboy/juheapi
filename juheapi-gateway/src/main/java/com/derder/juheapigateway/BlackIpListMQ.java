//package com.derder.juheapigateway;
//
//import com.derder.service.InnerIpBlackService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//@EnableDubbo
//public class BlackIpListMQ {
//    public static Set<Object> blackIpList = new HashSet<>();
//
//    @DubboReference
//    private InnerIpBlackService innerIpBlackService;
//    @RabbitListener(queues = "ipBlack.queue")
//    public void setBlackIpList(String message) {
//        blackIpList = null;
//        blackIpList = innerIpBlackService.getBlackIpList();
//        if(blackIpList.isEmpty()){
//            // 没有黑名单IP，则清空黑名单列表,并赋值一个空字符
//            BlackIpListMQ.blackIpList = new HashSet<>();
//            BlackIpListMQ.blackIpList.add("");
//        }
//    }
//}
