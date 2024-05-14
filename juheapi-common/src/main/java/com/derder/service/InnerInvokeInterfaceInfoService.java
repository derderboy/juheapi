package com.derder.service;

public interface InnerInvokeInterfaceInfoService {
    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    boolean addInvokeInterfaceInfo(Long uid, Long interfaceid, String ip);
}
