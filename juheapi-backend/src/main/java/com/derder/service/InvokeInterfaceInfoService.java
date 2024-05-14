package com.derder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.derder.model.dto.invokeinterfaceinfo.InvokeInterfaceInfoAddRequest;
import com.derder.model.dto.invokeinterfaceinfo.InvokeInterfaceInfoQueryRequest;
import com.derder.model.entity.InterfaceInfo;
import com.derder.model.entity.InvokeInterfaceInfo;

/**
* @author derder
* @description 针对表【invoke_interface_info(调用接口)】的数据库操作Service
* @createDate 2024-05-12 22:19:06
*/
public interface InvokeInterfaceInfoService extends IService<InvokeInterfaceInfo> {
    /**
     * 添加调用接口记录
     * @param invokeInterfaceInfoAddRequest  添加参数
     * @return String
     */
    public String addInvokeInterfaceInfo(InvokeInterfaceInfoAddRequest invokeInterfaceInfoAddRequest);
    public String queryInvokeInterfaceInfo(InvokeInterfaceInfoQueryRequest invokeInterfaceInfoQueryRequest);
}
