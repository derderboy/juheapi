package com.derder.constant;

/**
* 地址常量
*
* @author derder
* @version 1.0
*/
public interface MyUrl {
    /**
    * 网关
    */
    String GATEWAY_HOST = "http://localhost:8090";
    /**
    * 获取随机数
    */
    String RANDOM_NUMBER = "/api/randomNumber";
    /**
    * 获取你的名字
    */
    String GET_YOURNAME = "/api/name/user";
    /**
    * 获取随机动物图片
    */
    String GET_ANIMALIMAGE = "/api/randomImage";
    /**
    * 今天吃什么
    */
    String Get_Food = "/api/food";
}
