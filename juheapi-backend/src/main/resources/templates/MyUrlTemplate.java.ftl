package com.derder.constant;

/**
* 地址常量
*
* @author derder
* @version 1.0
*/
public interface MyUrl {
<#list generateList as url>
    /**
    * ${url.description}
    */
    String ${url.urlname} = "${url.url}";
</#list>
}
