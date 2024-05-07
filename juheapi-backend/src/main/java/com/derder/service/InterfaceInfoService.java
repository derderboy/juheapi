package com.derder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.derder.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.derder.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author derder
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-04-19 15:26:34
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest postQueryRequest);

}
